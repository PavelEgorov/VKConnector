package com.egorovsoft.vkconnector.mvp.model.basictypes

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Link (
    @Expose val url:String,
    @Expose val title: String,
    @Expose val description: String
): Parcelable