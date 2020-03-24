package com.egorovsoft.vkconnector.rx

import io.reactivex.rxjava3.subjects.PublishSubject

class EventBass {

    companion object{
        fun getInstance() = EventBass()
    }

    /// Напрашивается .publish(), но у меня не получается его реализовать..
    // Похоже дело в том, что данные отсылаются быстрее чем я получаю и мне уже нечего получать (в случае с обновлением формы)
    // по этому поток использую один маин.
    val event = PublishSubject.create<EventString>()
    //val observer by lazy { event.publish() }
}