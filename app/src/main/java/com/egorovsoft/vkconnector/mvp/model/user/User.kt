package com.egorovsoft.vkconnector.mvp.model.user

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

///{{ Беру уже готового пользователя
//https://oauth.vk.com/blank.html
// #access_token=80fdad54558bb571db652ca0f5f6d8ae9b165a6b99053bef40cecb8d1332f57df249be201b7b23a3e4636
// &expires_in=86400
// &user_id=139860951
// &state=123456
@Parcelize
data class User (
    @Expose val id : Int,
    @Expose val firstName : String,
    @Expose val lastName : String,
    @Expose val photo_50 : String
): Parcelable