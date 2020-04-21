package com.egorovsoft.vkconnector.mvp.model.basictypes

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

///Это устаревший тип вложений. Он может быть возвращен лишь для записей, созданных раньше 2013 года.
@Parcelize
data class App (
    @Expose val id:Int,
    @Expose val ownerInt: Int,
    @Expose val photo_130: String,
    @Expose val photo_604: String
): Parcelable