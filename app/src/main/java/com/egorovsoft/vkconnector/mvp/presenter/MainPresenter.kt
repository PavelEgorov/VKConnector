package com.egorovsoft.vkconnector.mvp.presenter

import com.egorovsoft.vkconnector.mvp.model.Const
import com.egorovsoft.vkconnector.mvp.model.room.cache.token.ICurrentSessionInfoCache
import com.egorovsoft.vkconnector.mvp.model.token.AutorizeModel
import com.egorovsoft.vkconnector.mvp.model.token.CurrentSessionInfo
import com.egorovsoft.vkconnector.mvp.model.user.User
import com.egorovsoft.vkconnector.mvp.model.user.UserModel
import com.egorovsoft.vkconnector.mvp.view.MainView
import com.egorovsoft.vkconnector.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class MainPresenter(
    val mainThread: Scheduler
) : MvpPresenter<MainView>() {

    @Inject lateinit var router: Router
    @Inject lateinit var user: UserModel
    @Inject lateinit var currentSessionInfo: ICurrentSessionInfoCache

    private var currentUser: User? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        currentSessionInfo.getCurrentSessionInfo(Const.programID)
            .flatMap {
                user.getUser(it.currentToken, it.userId)
            }
            .observeOn(mainThread)
            .subscribe(
                {
                    currentUser = it
                    currentUser?.let {
                        viewState.setUserName("${it.firstName} ${it.lastName}")
                        viewState.loadPhoto(it.photo_50)
                    }

                    menuWallSelected()
                },
                {
                    Timber.e(it)
                }
            )
    }

    fun menuWallSelected(){
        router.navigateTo(Screens.WallScreen())
    }

    fun menuFriendselected(){
        router.navigateTo(Screens.FriendsScreen())
    }

    fun menuMessageSelected(){
        router.navigateTo(Screens.ConversationScreen())
    }
}