package com.egorovsoft.vkconnector.mvp.view.fragment.conversation

interface IRvConversationPresenter {
    var onClickListener : ((IViewHolderConversation) -> Unit)?
    fun bindViewHolder(holder: IViewHolderConversation)
    fun getItemCount():Int
}