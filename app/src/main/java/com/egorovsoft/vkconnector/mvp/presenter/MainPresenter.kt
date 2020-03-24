package com.egorovsoft.vkconnector.mvp.presenter

import com.egorovsoft.vkconnector.mvp.view.MainView
import com.egorovsoft.vkconnector.navigation.Screens
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Screen

@InjectViewState
class MainPresenter(val router: Router) : MvpPresenter<MainView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.NewsScreen())
    }
}