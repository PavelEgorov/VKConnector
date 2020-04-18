package com.egorovsoft.vkconnector.mvp.view.fragment.wall

interface IViewHolderWall {
    var pos: Int

    fun setText(txt: String)
    fun setIcon(path: String)
    fun setTitle(txt: String)

    fun addImage(path: String)
    fun addText(txt: String)
}