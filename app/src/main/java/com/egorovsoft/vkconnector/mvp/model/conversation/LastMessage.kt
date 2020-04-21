package com.egorovsoft.vkconnector.mvp.model.conversation

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LastMessage (
    @Expose val id:Int,
    @Expose val peerId:Int,
    @Expose val fromId:Int,
    @Expose val text:String
):Parcelable