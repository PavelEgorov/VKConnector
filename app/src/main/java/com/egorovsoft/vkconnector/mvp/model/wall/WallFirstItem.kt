package com.egorovsoft.vkconnector.mvp.model.wall

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WallFirstItem(
    val id: Int,
    val userId: Int,
    val userText: String?,
    val photo: String?,
    val text: String?
): Parcelable