package com.egorovsoft.vkconnector.mvp.model.basictypes

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Poll (
    @Expose val id: Int,
    @Expose val ownerId: Int,
    @Expose val question: String,
    @Expose val votes: String
): Parcelable