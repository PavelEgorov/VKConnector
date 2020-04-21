package com.egorovsoft.vkconnector.mvp.model.conversation.messages

import android.os.Parcelable
import com.egorovsoft.vkconnector.mvp.model.attachments.ItemAttachmentsWall
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Message(
    @Expose val id: Int,
    @Expose val date: Int,
    @Expose val peer_id: Int,
    @Expose val from_id: Int,
    @Expose val text: String,
    @Expose val attachments: ItemAttachmentsWall
):Parcelable