package com.egorovsoft.vkconnector.mvp.model.conversation

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Peer(
    @Expose val id: Int,
    @Expose val type: String,
    @Expose val localId: Int
):Parcelable