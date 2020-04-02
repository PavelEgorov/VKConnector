package com.egorovsoft.vkconnector.mvp.model.wall

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Wall (
    @Expose val count: Int,
    @Expose val items: ArrayList<WallItem>
): Parcelable