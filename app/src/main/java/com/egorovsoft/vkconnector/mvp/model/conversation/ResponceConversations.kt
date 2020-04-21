package com.egorovsoft.vkconnector.mvp.model.conversation

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponceConversations (
    @Expose val count: Int,
    @Expose val item: List<Conversations>
):Parcelable