package com.egorovsoft.vkconnector.mvp.model.room.cache.wall

import com.egorovsoft.vkconnector.mvp.model.room.RoomWall
import com.egorovsoft.vkconnector.mvp.model.room.basicroom.*
import com.egorovsoft.vkconnector.mvp.model.room.cache.user.IUserCache
import com.egorovsoft.vkconnector.mvp.model.room.db.Database
import com.egorovsoft.vkconnector.mvp.model.wall.WallFirstItem
import com.egorovsoft.vkconnector.mvp.model.wall.WallItem
import com.egorovsoft.vkconnector.mvp.model.wall.WallRvItem
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomWallCache(val database: Database): IWallCache {
    override fun putUserWall(wall: List<WallItem>)= Completable.fromAction {
        val roomWallCache= mutableListOf<RoomWall>()
        roomWallCache.addAll(wall.map {
            val roomWall = RoomWall(
                it.id,
                it.ownerId,
                it.fromId,
                it.date,
                it.text,
                it.postType,
                it.replyOwnerid,
                it.replyPostId
            )

           roomWall
        })
        
        database.wallDao.insert(roomWallCache)
    }.subscribeOn(Schedulers.io())

    override fun getWallUser(userId: Int) = Single.fromCallable {
        return@fromCallable database.wallDao.getUserWall(userId)?.map { roomWall ->
            WallItem(
                roomWall.id,
                roomWall.ownerId,
                roomWall.fromId,
                roomWall.date,
                roomWall.text,
                roomWall.postType,
                roomWall.replyOwnerid,
                roomWall.replyPostId,
                null,
                null
            )
        }
    }.subscribeOn(Schedulers.io())
}