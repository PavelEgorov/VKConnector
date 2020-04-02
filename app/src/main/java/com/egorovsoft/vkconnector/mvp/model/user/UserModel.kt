package com.egorovsoft.vkconnector.mvp.model.user

import com.egorovsoft.vkconnector.mvp.model.IVKApi
import io.reactivex.rxjava3.schedulers.Schedulers

class UserModel(val api: IVKApi) {
    fun authorize() = api.authorize().subscribeOn(Schedulers.io())
    fun getUser(token: String, userId: Int) = api.userGet(token, userId).subscribeOn(Schedulers.io())
}