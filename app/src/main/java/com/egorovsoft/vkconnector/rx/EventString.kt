package com.egorovsoft.vkconnector.rx

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventString (
    val className : String, /// По идее можно указать Any и передать класс, но я не знаю насколько это верный ход. По этому делаю примитивно.
    val funName: String,
    val result: String /// Аналогично можно повесить Any, и тогда можно будет выполнять любые функции. Но не знаю насколько это верный ход.
): Parcelable /// так же не понятно, обязательно ли делать Parcelable, возможно можно просто передать класс. Все таки не с формой работаем.
              /// но между потоками я бы обменивался простыми типами.