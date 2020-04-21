package com.egorovsoft.vkconnector.mvp.presenter.fragment

import com.egorovsoft.vkconnector.mvp.model.Const
import com.egorovsoft.vkconnector.mvp.model.attachments.ItemAttachmentsWall
import com.egorovsoft.vkconnector.mvp.model.room.cache.token.ICurrentSessionInfoCache
import com.egorovsoft.vkconnector.mvp.model.room.cache.token.RoomCurrentSessionCache
import com.egorovsoft.vkconnector.mvp.model.token.CurrentSessionInfo
import com.egorovsoft.vkconnector.mvp.model.user.User
import com.egorovsoft.vkconnector.mvp.model.user.UserModel
import com.egorovsoft.vkconnector.mvp.model.wall.*
import com.egorovsoft.vkconnector.mvp.view.fragment.wall.IRvWallPresenter
import com.egorovsoft.vkconnector.mvp.view.fragment.wall.IViewHolderWall
import com.egorovsoft.vkconnector.mvp.view.fragment.wall.WallView
import com.egorovsoft.vkconnector.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class WallPresenter(
    val mainThread: Scheduler
) : MvpPresenter<WallView>() {

    @Inject
    lateinit var wallModel: WallModel

    @Inject
    lateinit var userModel: UserModel

    @Inject
    lateinit var roomCurrentSessionCache: ICurrentSessionInfoCache

    private var currentSessionInfo: CurrentSessionInfo? = null

    class RvPresenter(val mainThread: Scheduler) : IRvWallPresenter {
        @Inject lateinit var userModel: UserModel

        var token: String? = null
        val wall = mutableListOf<WallFirstItem>()

        override var onClickListener: ((IViewHolderWall) -> Unit)? = null

        override fun bindViewHolder(holder: IViewHolderWall) {
            wall[holder.pos].photo?.let {
                holder.setPhoto(it)
            }?: holder.setPhoto(null)

            wall[holder.pos].userText?.let {
                holder.setUserText(it)
            }?: holder.setUserText(null)

            wall[holder.pos].text?.let {
                holder.setText(it)
            }?: holder.setText(null)

            token?.let {
                userModel.getUser(it, wall[holder.pos].userId)
                    .observeOn(mainThread)
                    .subscribe(
                        {
                            holder.setIcon(it.photo_50)
                            holder.setTitle(it.firstName)
                        }, {
                            holder.setIcon(null)
                            holder.setTitle(null)
                            Timber.e(it)
                        })
            }
        }

        override fun getItemCount(): Int = wall.size
    }

    val rvPresenter = RvPresenter(mainThread)

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()

        roomCurrentSessionCache.getCurrentSessionInfo(Const.programID)
            .flatMap { currentSession ->
                currentSessionInfo = currentSession
                rvPresenter.token = currentSession.currentToken

                wallModel.getWall(currentSession.currentToken, currentSession.userId)
            }
            .observeOn(mainThread)
            .subscribe(
                {
                    rvPresenter.wall.clear()
                    rvPresenter.wall.addAll(it)

                    viewState.updateList()
                },
                {
                    Timber.e(it)
                }
            )
    }
}