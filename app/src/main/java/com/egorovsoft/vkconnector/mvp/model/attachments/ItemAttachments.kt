package com.egorovsoft.vkconnector.mvp.model.attachments

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemAttachments (
    @Expose val title: String,
    @Expose val description: String
): Parcelable