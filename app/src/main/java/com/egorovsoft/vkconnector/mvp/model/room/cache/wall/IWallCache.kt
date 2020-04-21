package com.egorovsoft.vkconnector.mvp.model.room.cache.wall

import com.egorovsoft.vkconnector.mvp.model.wall.WallFirstItem
import com.egorovsoft.vkconnector.mvp.model.wall.WallItem
import com.egorovsoft.vkconnector.mvp.model.wall.WallRvItem
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IWallCache {
    fun putUserWall(wall: List<WallItem>): Completable
    fun getWallUser(userId: Int): Single<List<WallItem>>
}