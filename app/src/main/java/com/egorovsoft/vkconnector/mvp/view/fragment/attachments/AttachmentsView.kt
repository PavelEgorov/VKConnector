package com.egorovsoft.vkconnector.mvp.view.fragment.attachments

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface AttachmentsView :MvpView{
    fun init()
    fun setTitle(txt: String)
    fun setDescription(txt: String)
}