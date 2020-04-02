package com.egorovsoft.vkconnector.mvp.model.wall

import android.os.Parcelable
import com.egorovsoft.vkconnector.mvp.model.attachments.ItemAttachments
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WallItem (
    @Expose val id: Int, // идентификатор записи.
    @Expose val ownerId: Int, // идентификатор владельца стены, на которой размещена запись
    @Expose val fromId: Int, // идентификатор автора записи (от чьего имени опубликована запись).
    @Expose val date: Int, // время публикации записи в формате unixtime.
    @Expose val text: String, // текст записи.
    @Expose val postType: String, // тип записи, может принимать следующие значения: post, copy, reply, postpone, suggest.
    @Expose val attachments: MutableList<ItemAttachments> // медиавложения записи (фотографии, ссылки и т.п.)
): Parcelable