package com.egorovsoft.vkconnector.mvp.presenter

import com.egorovsoft.vkconnector.mvp.model.Const
import com.egorovsoft.vkconnector.mvp.model.IVKAuthorize
import com.egorovsoft.vkconnector.mvp.model.room.cache.token.ICurrentSessionInfoCache
import com.egorovsoft.vkconnector.mvp.model.token.AutorizeModel
import com.egorovsoft.vkconnector.mvp.model.token.CurrentSessionInfo
import com.egorovsoft.vkconnector.mvp.view.SplashView
import io.reactivex.rxjava3.core.Scheduler
import moxy.InjectViewState
import moxy.MvpPresenter
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class SplashPresenter(val mainThread: Scheduler): MvpPresenter<SplashView>() {

    @Inject lateinit var auth: AutorizeModel
    @Inject lateinit var roomCurrentSessionInfo: ICurrentSessionInfoCache

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        autorize()
    }

    fun autorize() {
        /// TODO: Нужна прверка на то, истек ли срок действия ключа
        roomCurrentSessionInfo.getCurrentSessionInfo(Const.programID)
            .flatMap {
                auth.authorize()
            }
            .observeOn(mainThread)
            .subscribe({
                viewState.showAuthorization(it)
            }, {e ->
                Timber.e(e)
            })
    }

    fun loadUrl(url: String) {
        viewState.loadUrl(url)
    }

    fun onAuthorizated(url: String){
        if (url.substringBefore("#").equals(Const.redirectUri)) {
            viewState.hideAuthorization()

            val token = url.substringAfter("access_token=").substringBefore("&")
            val date = url.substringAfter("expires_in=").substringBefore("&").toInt()
            val userId = url.substringAfter("user_id=").toInt()

            roomCurrentSessionInfo.putCurrentSessionInfo(CurrentSessionInfo(Const.programID, userId, token, date))
                .observeOn(mainThread)
                .subscribe()

            viewState.openMainScreen()
        }
    }
}