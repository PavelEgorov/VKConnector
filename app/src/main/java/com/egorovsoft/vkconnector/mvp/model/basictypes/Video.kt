package com.egorovsoft.vkconnector.mvp.model.basictypes

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Video (
    @Expose val id: Int,
    @Expose val ownerId: Int,
    @Expose val title: String,
    @Expose val description: String,
    @Expose val player: String
): Parcelable