package com.egorovsoft.vkconnector.mvp.view.fragment.wall

interface IRvWallPresenter {
    var onClickListener : ((IViewHolderWall) -> Unit)?
    fun bindViewHolder(holder: IViewHolderWall)
    fun getItemCount():Int
}