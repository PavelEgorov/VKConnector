package com.egorovsoft.vkconnector.mvp.model.room.cache.basic

import com.egorovsoft.vkconnector.mvp.model.basictypes.Photo
import com.egorovsoft.vkconnector.mvp.model.basictypes.Sizes
import com.egorovsoft.vkconnector.mvp.model.room.basicroom.RoomPhoto
import com.egorovsoft.vkconnector.mvp.model.room.db.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomPhotoCache(val database: Database): IPhotoCache {
    override fun putPhoto(photo: Photo, wallId: Int)= Completable.fromAction {
        val room = database.photoDao.findById(photo.id)?.apply {
            this.id = photo.id
            this.albumId = photo.albumId
            this.ownerId = photo.ownerId
            this.text = photo.text
            this.wallId = wallId
        } ?: RoomPhoto(photo.id, wallId, photo.albumId, photo.ownerId, photo.text)

        database.photoDao.insert(room)
    }.subscribeOn(Schedulers.io())

    override fun getWallPhotos(wallId: Int)= Single.fromCallable {
        return@fromCallable database.photoDao.getAllWallPhoto(wallId)?.run {
            this.map {
                Photo(
                    it.id,
                    it.albumId,
                    it.ownerId,
                    it.text,
                    null
                )
            }
        }
    }.subscribeOn(Schedulers.io())

    override fun getFirstWallPhoto(wallId: Int)= Single.fromCallable {
        return@fromCallable database.photoDao.getFirstWallPhoto(wallId)?.run {
            Photo(this.id, this.albumId, this.ownerId,this.text,null)
        }?: throw Throwable("No surch photo")
    }.subscribeOn(Schedulers.io())
}