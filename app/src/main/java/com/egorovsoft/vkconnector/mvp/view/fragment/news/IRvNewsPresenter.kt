package com.egorovsoft.vkconnector.mvp.view.fragment.news

interface IRvNewsPresenter {
    var onClickListener : ((IViewHolderNews) -> Unit)?
    fun bindViewHolder(holder: IViewHolderNews)
    fun getItemCount():Int
}