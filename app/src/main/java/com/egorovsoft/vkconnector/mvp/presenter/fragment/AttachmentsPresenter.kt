package com.egorovsoft.vkconnector.mvp.presenter.fragment

import com.egorovsoft.vkconnector.mvp.model.attachments.ItemAttachments
import com.egorovsoft.vkconnector.mvp.view.fragment.attachments.AttachmentsView
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class AttachmentsPresenter(val mainThread: Scheduler) : MvpPresenter<AttachmentsView>() {
    var item : ItemAttachments? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

        updateFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun updateFragment() {
        item?.let {
            /// Отправляю данные на изменение
            viewState.setTitle(it.title)
            viewState.setDescription(it.description)
        }
    }
}