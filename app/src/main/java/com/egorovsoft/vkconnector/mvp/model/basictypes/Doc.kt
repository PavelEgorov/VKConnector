package com.egorovsoft.vkconnector.mvp.model.basictypes

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Doc (
    @Expose val id:Int,
    @Expose val ownerInt: Int,
    @Expose val title: String,
    @Expose val url: String
): Parcelable