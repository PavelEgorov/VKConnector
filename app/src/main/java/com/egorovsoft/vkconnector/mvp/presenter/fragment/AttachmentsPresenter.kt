package com.egorovsoft.vkconnector.mvp.presenter.fragment

import com.egorovsoft.vkconnector.mvp.model.attachments.ItemAttachments
import com.egorovsoft.vkconnector.mvp.view.fragment.attachments.AttachmentsView
import com.egorovsoft.vkconnector.rx.EventBass
import com.egorovsoft.vkconnector.rx.EventString
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class AttachmentsPresenter(val mainThread: Scheduler) : MvpPresenter<AttachmentsView>() {
    var item : ItemAttachments? = null
    val bass = EventBass.getInstance().event
    var disposable : Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

        /// Пример использования, обновление форм

        /// Присоединяюсь к автобусу
        //disposable = EventBass.getInstance().observer.connect()

        /// подписываюсь
        disposable = bass
            /// Странно когда вызываю подписку в разных потоках то не обновляется. Возможно дело в потоке. Не успевает получить значения
//            .subscribeOn(Schedulers.io())
//            .observeOn(mainThread)
            .filter {
                it.className == this::class.toString()
            }
            .doOnNext{
                if (it.funName == "setTitle"){
                    viewState.setTitle(it.result)
                }
                if (it.funName == "setDescription"){
                    viewState.setDescription(it.result)
                }
            }
            .subscribe()

        updateFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable!!.dispose()
    }

    fun updateFragment() {
        item?.let {
            /// Отправляю данные на изменение
            bass.onNext(EventString(this::class.toString(), "setTitle", it.title));
            bass.onNext(EventString(this::class.toString(), "setDescription", it.description));
        }
    }
}