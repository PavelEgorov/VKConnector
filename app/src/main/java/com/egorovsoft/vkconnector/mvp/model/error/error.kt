package com.egorovsoft.vkconnector.mvp.model.error

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class error(
    val error: String,
    val errorDescription: String
) : Parcelable