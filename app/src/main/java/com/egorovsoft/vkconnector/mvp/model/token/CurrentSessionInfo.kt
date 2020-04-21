package com.egorovsoft.vkconnector.mvp.model.token

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrentSessionInfo (
    @Expose val appId: Int,
    @Expose val userId: Int,
    @Expose val currentToken: String,
    @Expose val date: Int
): Parcelable