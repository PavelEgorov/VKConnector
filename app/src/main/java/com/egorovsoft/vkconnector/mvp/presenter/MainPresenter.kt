package com.egorovsoft.vkconnector.mvp.presenter

import com.egorovsoft.vkconnector.mvp.model.token.AutorizeModel
import com.egorovsoft.vkconnector.mvp.model.token.ResponseToken
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

    private var token =
        "e70cf4e2122d0ad2272449dc7a4c57289500349056d274ce6da528d068ca8aafa24ae6c9a17eb79f6e0be"
    private var userId = 139860951

    private var currentUser: User? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        /// В данный момент авторзацию я не делал.
        /// Точнее в том виде как она реализована, авторизация не сработает.
        /// нужно передавать данные во вью. Потом разберусь и доделаю
        /// На примере данной реализации я просто просмотрел работу flatMap
        //autorize()
        loadUser()
    }

    fun autorize() {
        auth.authorize()
            .observeOn(mainThread)
            .flatMap {
                /// Нужна проверка на пустой массив и на результат ошибки
                /// Возвращается строка. Так же нужно проверить истек предыдущий ключ или нет.
                /// но это будет потом. Пока тупо получаю ключ.

                /// VK API возвращает страницу с авторизацией, которую нужно будет показать пользователю.
                /// и после авторизации перенаправить уже на получение информации по пользователю.
                //token = it
                //userId = it

                user.getUser(token, userId)
            }
            .subscribe(
                {
                    /// Нужна проверка на пустой массив и на результат ошибки
                    currentUser = it.response[0]

                    currentUser?.let {
                        viewState.setUserName("${it.firstName} ${it.lastName}")
                        viewState.loadPhoto(it.photo_50)

                        router.replaceScreen(Screens.WallScreen(token, userId))
                    }
                },
                { e ->
                    Timber.e(e)
                }
            )
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

                        router.replaceScreen(Screens.WallScreen(token, userId))
                    }
                },
                { e ->
                    Timber.e(e)
                }
            )
    }
}