package com.egorovsoft.vkconnector.mvp.view.fragment.conversation

interface IViewHolderConversation {
    var pos: Int

    fun setLastMessage(txt: String)
}