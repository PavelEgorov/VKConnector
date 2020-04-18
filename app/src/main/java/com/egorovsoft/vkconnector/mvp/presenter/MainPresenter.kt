package com.egorovsoft.vkconnector.mvp.presenter

import com.egorovsoft.vkconnector.mvp.model.Const
import com.egorovsoft.vkconnector.mvp.model.token.AutorizeModel
import com.egorovsoft.vkconnector.mvp.model.user.User
import com.egorovsoft.vkconnector.mvp.model.user.UserModel
import com.egorovsoft.vkconnector.mvp.view.MainView
import com.egorovsoft.vkconnector.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import timber.log.Timber

@InjectViewState
class MainPresenter(
    val router: Router,
    val mainThread: Scheduler,
    val auth: AutorizeModel,
    val user: UserModel
) : MvpPresenter<MainView>() {

    //TODO: Необходимо сделать сохранение данных в шаред преференс
    // по мимо токена и ID пользователя, нам ещё пригодится время жизни ключа, чтобы понимать когда его нужно продлять.
    private var token =
        "e70cf4e2122d0ad2272449dc7a4c57289500349056d274ce6da528d068ca8aafa24ae6c9a17eb79f6e0be"
    private var userId = 139860951

    private var currentUser: User? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        autorize()
    }

    fun autorize() {
        auth.authorize()
            .observeOn(mainThread)
            .subscribe(
                {
                    viewState.showAuthorization(it)
                },
                { e ->
                    Timber.e(e)
                }
            )
    }

    fun loadUrl(url: String) {
        viewState.loadUrl(url)
    }

    fun onAuthorizated(url: String){
        if (url.substringBefore("#").equals(Const.redirectUri)) {
            viewState.hideAuthorization()

            token = url.substringAfter("access_token=").substringBefore("&")
            userId = url.substringAfter("user_id=").toInt()

            loadUser()
        }
    }

    fun loadUser() {
        user.getUser(token, userId)
            .observeOn(mainThread)
            .subscribe(
                {
                    /// Нужна проверка на пустой массив и на результат ошибки
                    currentUser = it.response[0]

                    currentUser?.let {
                        viewState.setUserName("${it.firstName} ${it.lastName}")
                        viewState.loadPhoto(it.photo_50)

                        //router.replaceScreen(Screens.WallScreen(token, userId)) это будет определяться кнопкой
                    }
                },
                { e ->
                    Timber.e(e)
                }
            )
    }

    fun menuWallSelected(){
        /// TODO: РЕШЕН нужно использовать navigateTo, но тогда вопрос при открытии старого экрана
        // ВОПРОС!! Что будет со старым фрагментом, если я сделаю replaceScreen
        /// На данный момент приложение падает с ошибкой, возможно нужен другой метод
        ///TODO: Разобраться с методами чичерони по переключению экранов
        router.navigateTo(Screens.WallScreen(token, userId))
    }

    fun menuNewsSelected(){
        router.navigateTo(Screens.NewsScreen())
    }

    fun menuMessageSelected(){
        /// TODO: Сделать загрузку личных сообщений
        /// TODO: Сделать фрагмент личных сообщений
        /// TODO: Сделать кэш личных сообщений
    }
}