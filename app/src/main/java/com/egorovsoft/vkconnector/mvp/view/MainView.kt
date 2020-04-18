package com.egorovsoft.vkconnector.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView:MvpView {
    fun showAuthorization(page: String)
    fun hideAuthorization()

    fun loadUrl(url: String)

    fun setUserName(txt: String)
    fun loadPhoto(path: String)
}