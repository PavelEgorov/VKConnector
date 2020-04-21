package com.egorovsoft.vkconnector.mvp.view.fragment.friends

interface IRvFriendsPresenter {
    var onClickListener : ((IViewHolderFriends) -> Unit)?
    fun bindViewHolder(holder: IViewHolderFriends)
    fun getItemCount():Int
}