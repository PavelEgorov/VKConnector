package com.egorovsoft.vkconnector.mvp.presenter.fragment

import com.egorovsoft.vkconnector.mvp.model.conversation.ConversationModel
import com.egorovsoft.vkconnector.mvp.model.conversation.Conversations
import com.egorovsoft.vkconnector.mvp.view.fragment.conversation.ConversationView
import com.egorovsoft.vkconnector.mvp.view.fragment.conversation.IRvConversationPresenter
import com.egorovsoft.vkconnector.mvp.view.fragment.conversation.IViewHolderConversation
import io.reactivex.rxjava3.core.Scheduler
import moxy.InjectViewState
import moxy.MvpPresenter
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class ConversationPresenter(val mainThread: Scheduler): MvpPresenter<ConversationView>() {

    class RvPresenter(): IRvConversationPresenter {
        val conversations = mutableListOf<Conversations>()

        override var onClickListener: ((IViewHolderConversation) -> Unit)? = null

        override fun bindViewHolder(holder: IViewHolderConversation) {
            holder.setLastMessage("${conversations[holder.pos].lastMessage.text}");
        }

        override fun getItemCount(): Int = conversations.size

    }

    @Inject lateinit var conversationModel: ConversationModel

    val rvPresenter = RvPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        update()
    }

    fun update(){
        conversationModel.getConversations()
            .observeOn(mainThread)
            .subscribe(
                {
                    rvPresenter.conversations.clear()
                    rvPresenter.conversations.addAll(it)

                    viewState.update()
                },
                {
                    Timber.e(it)
                }
            )
    }
}