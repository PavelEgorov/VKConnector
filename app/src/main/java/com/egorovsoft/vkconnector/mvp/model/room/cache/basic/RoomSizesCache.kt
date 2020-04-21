package com.egorovsoft.vkconnector.mvp.model.room.cache.basic

import com.egorovsoft.vkconnector.mvp.model.basictypes.Sizes
import com.egorovsoft.vkconnector.mvp.model.room.basicroom.RoomSizes
import com.egorovsoft.vkconnector.mvp.model.room.db.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomSizesCache(val database: Database): ISizesCache {
    override fun putSizes(sizes: List<Sizes>, photoId: Int)= Completable.fromAction {
        val sizesList = sizes.map {
            val room = database.sizesDao.findByUrl(it.url)?.apply {
                this.photoId = photoId
                this.type = it.type
                this.url = it.url
            } ?: RoomSizes(it.url, photoId, it.type)

            room
        }
        database.sizesDao.insert(sizesList)
    }.subscribeOn(Schedulers.io())

    override fun getPhotoSizes(photoId: Int)= Single.fromCallable {
        return@fromCallable database.sizesDao.getAllPhotoSizes(photoId)?.run {
            this.map {
                Sizes(it.type, it.url)
            }
        }
    }.subscribeOn(Schedulers.io())

    override fun getMPhotoSizes(photoId: Int)= Single.fromCallable {
        return@fromCallable database.sizesDao.getMPhotoSize(photoId)?.run {
            Sizes(this.type, this.url)
        }?: throw Throwable("No surch url photo type M")
    }.subscribeOn(Schedulers.io())
}