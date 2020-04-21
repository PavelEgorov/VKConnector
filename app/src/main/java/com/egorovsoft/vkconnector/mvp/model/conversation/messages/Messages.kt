package com.egorovsoft.vkconnector.mvp.model.conversation.messages

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Messages(
    @Expose val count:Int,
    @Expose val item: List<Message>
): Parcelable