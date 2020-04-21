package com.egorovsoft.vkconnector.mvp.presenter.fragment

import com.egorovsoft.vkconnector.mvp.model.friends.FriendsModel
import com.egorovsoft.vkconnector.mvp.model.room.cache.token.ICurrentSessionInfoCache
import com.egorovsoft.vkconnector.mvp.model.user.User
import com.egorovsoft.vkconnector.mvp.model.user.UserModel
import com.egorovsoft.vkconnector.mvp.view.fragment.friends.FriendsView
import com.egorovsoft.vkconnector.mvp.view.fragment.friends.IRvFriendsPresenter
import com.egorovsoft.vkconnector.mvp.view.fragment.friends.IViewHolderFriends
import io.reactivex.rxjava3.core.Scheduler
import moxy.InjectViewState
import moxy.MvpPresenter
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class FriendsPresenter(val mainThread: Scheduler): MvpPresenter<FriendsView>() {

    @Inject
    lateinit var friendsModel: FriendsModel

    val rvPresenter = FriendsRvAdapterPresenter()

    class FriendsRvAdapterPresenter(): IRvFriendsPresenter {
        val friends = mutableListOf<User>()

        override var onClickListener: ((IViewHolderFriends) -> Unit)? = null

        override fun bindViewHolder(holder: IViewHolderFriends) {
            holder.setText("${friends[holder.pos].firstName} ${friends[holder.pos].lastName}");
            holder.setImage(friends[holder.pos].photo_50)
        }

        override fun getItemCount(): Int = friends.size

    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

        update()
    }

    fun update(){
        friendsModel.getFriends()
            .observeOn(mainThread)
            .subscribe({
                rvPresenter.friends.clear()
                rvPresenter.friends.addAll(it)

                viewState.updateList()
            },
                {
                    Timber.e(it)
                })
    }
}