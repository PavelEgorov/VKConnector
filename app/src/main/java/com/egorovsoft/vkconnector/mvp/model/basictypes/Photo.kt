package com.egorovsoft.vkconnector.mvp.model.basictypes

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    @Expose val id: Int,
    @Expose val albumId: Int,
    @Expose val ownerId: Int,
    @Expose val text: String,
    @Expose val sizes: MutableList<Sizes>
): Parcelable