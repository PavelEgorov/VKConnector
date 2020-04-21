package com.egorovsoft.vkconnector.mvp.view.fragment.conversation

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ConversationView: MvpView {
    fun init()
    fun update()
}