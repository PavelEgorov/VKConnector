package com.egorovsoft.vkconnector.mvp.view.fragment.wall

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface WallView : MvpView {
    fun init()
    fun updateList()
}