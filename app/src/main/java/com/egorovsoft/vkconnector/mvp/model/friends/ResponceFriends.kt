package com.egorovsoft.vkconnector.mvp.model.friends

import android.os.Parcelable
import com.egorovsoft.vkconnector.mvp.model.user.User
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponceFriends(
    @Expose val count: Int,
    @Expose val items: List<User>
): Parcelable