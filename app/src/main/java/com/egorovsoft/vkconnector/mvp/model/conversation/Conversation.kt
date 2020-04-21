package com.egorovsoft.vkconnector.mvp.model.conversation

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Conversation (
    @Expose val peer: Peer,
    @Expose val unreadCount: Int
):Parcelable