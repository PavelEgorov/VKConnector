package com.egorovsoft.vkconnector.mvp.model.room.cache.user

import com.egorovsoft.vkconnector.mvp.model.user.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IUserCache {
    fun findUserById(id: Int): Single<User>
    fun putUser(user: User): Completable
    fun putUsers(users: List<User>): Completable
}