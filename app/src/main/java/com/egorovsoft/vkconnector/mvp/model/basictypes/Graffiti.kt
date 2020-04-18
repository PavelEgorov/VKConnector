package com.egorovsoft.vkconnector.mvp.model.basictypes

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Graffiti (
    @Expose val id:Int,
    @Expose val ownerInt: Int,
    @Expose val photo_130: String,
    @Expose val photo_604: String
): Parcelable