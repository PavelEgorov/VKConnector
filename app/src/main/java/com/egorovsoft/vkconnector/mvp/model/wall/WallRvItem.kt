package com.egorovsoft.vkconnector.mvp.model.wall

import android.os.Parcelable
import com.egorovsoft.vkconnector.mvp.model.user.User
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WallRvItem(
    val id: Int,
    val user: User,
    val userText: String?,
    val photo: String?,
    val text: String?
): Parcelable