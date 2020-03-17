package com.egorovsoft.vkconnector.mvp.view.fragment.news

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface NewsView : MvpView{
    fun init()
    fun updateList()
}