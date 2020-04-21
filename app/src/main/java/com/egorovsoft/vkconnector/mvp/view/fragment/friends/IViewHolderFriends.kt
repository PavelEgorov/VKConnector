package com.egorovsoft.vkconnector.mvp.view.fragment.friends

interface IViewHolderFriends {
    var pos: Int

    fun setText(txt: String)
    fun setImage(path: String)
}