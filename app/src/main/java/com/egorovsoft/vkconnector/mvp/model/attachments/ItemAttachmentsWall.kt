package com.egorovsoft.vkconnector.mvp.model.attachments

import android.os.Parcelable
import com.egorovsoft.vkconnector.mvp.model.basictypes.*
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemAttachmentsWall (
    @Expose val type: String,
    @Expose val photo: Photo,
    @Expose val postedPhoto: PostedPhoto,
    @Expose val video: Video,
    @Expose val audio: Audio,
    @Expose val doc: Doc,
    @Expose val graffiti: Graffiti,
    @Expose val link: Link,
    @Expose val note: Note,
    @Expose val app: App,
    @Expose val poll: Poll
///TODO: Вопрос Первое поле — type (string) содержит тип вложения (photo,note,audio и т.д.). Название второго поля совпадает со значением, переданным в type.
): Parcelable