package com.egorovsoft.vkconnector.mvp.presenter.fragment

import com.egorovsoft.vkconnector.mvp.model.attachments.ItemAttachments
import com.egorovsoft.vkconnector.mvp.view.fragment.attachments.AttachmentsView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class AttachmentsPresenter() : MvpPresenter<AttachmentsView>() {
    var item : ItemAttachments? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun updateFragment() {
        item?.let {
            viewState.setTitle(it.title)
            viewState.setDescription(it.description)
        }
    }
}