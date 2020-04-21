package com.egorovsoft.vkconnector.mvp.view.fragment.wall

interface IViewHolderWall {
    var pos: Int

    fun setUserText(txt: String?)
    fun setIcon(path: String?)
    fun setTitle(txt: String?)

    fun setPhoto(path: String?)
    fun setText(txt: String?)
}