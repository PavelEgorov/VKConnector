package com.egorovsoft.vkconnector.mvp.model.attachments

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemAttachments (
    val title: String,
    val description: String
): Parcelable