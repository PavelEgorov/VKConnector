package com.egorovsoft.vkconnector.ui.fragment.conversation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.egorovsoft.vkconnector.R
import com.egorovsoft.vkconnector.mvp.view.fragment.conversation.IRvConversationPresenter
import com.egorovsoft.vkconnector.mvp.view.fragment.conversation.IViewHolderConversation
import com.egorovsoft.vkconnector.mvp.view.fragment.friends.IRvFriendsPresenter
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.rv_convertation.*

class ConversationRvAdapter(val presenter : IRvConversationPresenter): RecyclerView.Adapter<ConversationRvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_convertation, parent, false))

    override fun getItemCount(): Int = presenter.getItemCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos  =   position;
        holder.containerView.setOnClickListener{
            presenter.onClickListener?.invoke(holder)
        }
        presenter.bindViewHolder(holder)
    }

    class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView),
        LayoutContainer, IViewHolderConversation {
        override var pos = -1

        override fun setLastMessage(txt: String) = with(containerView){
            tv_conversation_text.text = txt
        }

    }
}