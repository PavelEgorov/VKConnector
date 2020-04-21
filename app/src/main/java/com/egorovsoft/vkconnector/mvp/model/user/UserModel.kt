package com.egorovsoft.vkconnector.mvp.model.user

import com.egorovsoft.vkconnector.mvp.model.IVKApi
import com.egorovsoft.vkconnector.mvp.model.room.cache.user.IUserCache
import io.reactivex.rxjava3.schedulers.Schedulers

class UserModel(val api: IVKApi, val roomUser: IUserCache) {
    fun getUser(token: String, userId: Int) = api.userGet(token, userId)
        .flatMap { users ->
            users.response?.let {
                roomUser.putUsers(it).toSingleDefault(users)
                    .flatMap { roomUser.findUserById(userId) }
            } ?: roomUser.findUserById(userId)
        }
        .subscribeOn(Schedulers.io())
}