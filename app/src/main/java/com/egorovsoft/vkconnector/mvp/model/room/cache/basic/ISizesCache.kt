package com.egorovsoft.vkconnector.mvp.model.room.cache.basic

import com.egorovsoft.vkconnector.mvp.model.basictypes.Sizes
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface ISizesCache {
    fun putSizes(sizes: List<Sizes>, photoId: Int): Completable
    fun getPhotoSizes(photoId: Int): Single<List<Sizes>>
    fun getMPhotoSizes(photoId: Int):Single<Sizes>
}