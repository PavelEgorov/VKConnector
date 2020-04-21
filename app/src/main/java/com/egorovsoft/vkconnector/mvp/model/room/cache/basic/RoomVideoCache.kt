package com.egorovsoft.vkconnector.mvp.model.room.cache.basic

import com.egorovsoft.vkconnector.mvp.model.basictypes.Video
import com.egorovsoft.vkconnector.mvp.model.room.basicroom.RoomVideo
import com.egorovsoft.vkconnector.mvp.model.room.db.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomVideoCache(val database: Database): IVideoCache {
    override fun putVideo(video: Video, wallId: Int)= Completable.fromAction {
        val room = database.videoDao.findById(video.id)?.apply {
            this.description = video.description
            this.title = video.title
            this.player = video.player
            this.ownerId = video.ownerId
            this.id = video.id
            this.wallId = wallId
        } ?: RoomVideo(video.id, wallId, video.ownerId, video.title, video.description, video.player)

        database.videoDao.insert(room)
    }.subscribeOn(Schedulers.io())

    override fun getWallVideos(wallId: Int)= Single.fromCallable {
        return@fromCallable database.videoDao.getAllWallVideo(wallId)?.run {
            this.map {
                Video(it.id, it.ownerId,it.title,it.description,it.player)
            }
        }
    }.subscribeOn(Schedulers.io())

    override fun getFirstWallVideo(wallId: Int)= Single.fromCallable {
        return@fromCallable database.videoDao.getFirstWallVideo(wallId)?.run {
            Video(this.id, this.ownerId, this.title, this.description, this.player)
        }?: throw Throwable("No surch video")
    }.subscribeOn(Schedulers.io())
}