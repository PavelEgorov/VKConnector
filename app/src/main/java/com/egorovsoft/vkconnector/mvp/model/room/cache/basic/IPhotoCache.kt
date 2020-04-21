package com.egorovsoft.vkconnector.mvp.model.room.cache.basic

import com.egorovsoft.vkconnector.mvp.model.basictypes.Photo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IPhotoCache {
    fun putPhoto(photo: Photo, wallId: Int): Completable
    fun getWallPhotos(wallId: Int): Single<List<Photo>>
    fun getFirstWallPhoto(wallId: Int): Single<Photo>
}