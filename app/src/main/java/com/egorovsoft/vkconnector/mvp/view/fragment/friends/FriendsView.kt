package com.egorovsoft.vkconnector.mvp.view.fragment.friends

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface FriendsView: MvpView {
    fun init()
    fun updateList()
}