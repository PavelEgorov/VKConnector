package com.egorovsoft.vkconnector.mvp.model.token

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseToken (
    @Expose val accessToken: String,
    @Expose val expiresIn: String,
    @Expose val userId: Int
):Parcelable