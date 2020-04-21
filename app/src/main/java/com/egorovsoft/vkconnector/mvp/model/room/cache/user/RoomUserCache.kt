package com.egorovsoft.vkconnector.mvp.model.room.cache.user

import com.egorovsoft.vkconnector.mvp.model.room.RoomUser
import com.egorovsoft.vkconnector.mvp.model.room.db.Database
import com.egorovsoft.vkconnector.mvp.model.user.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomUserCache(val database: Database): IUserCache {
    override fun findUserById(id: Int) = Single.fromCallable {
        return@fromCallable database.userDao.findById(id)?.run{
            User(this.id, this.firstName, this.lastName, this.photo_50)
        } ?: User(0, "", "", "")
    }.subscribeOn(Schedulers.io())

    override fun putUser(user: User)= Completable.fromAction {
        val roomUser = database.userDao.findById(user.id)?.apply {
            this.id = user.id
            this.firstName = user.firstName
            this.lastName = user.lastName
            this.photo_50 = user.photo_50
        } ?: RoomUser(user.id, user.firstName, user.lastName, user.photo_50)

        database.userDao.insert(roomUser)
    }.subscribeOn(Schedulers.io())

    override fun putUsers(users: List<User>) = Completable.fromAction {
        val roomUsers = users.map { user -> RoomUser(user.id, user.firstName, user.lastName, user.photo_50) }
        database.userDao.insert(roomUsers)
    }.subscribeOn(Schedulers.io())
}