package com.egorovsoft.vkconnector.mvp.presenter.fragment

import com.egorovsoft.vkconnector.mvp.model.attachments.ItemAttachments
import com.egorovsoft.vkconnector.mvp.view.fragment.attachments.AttachmentsView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class AttachmentsPresenter(val item: ItemAttachments) : MvpPresenter<AttachmentsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.setTitle(item.title)
        viewState.setDescription(item.description)
    }
}