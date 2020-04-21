package com.egorovsoft.vkconnector.mvp.model.room.cache.basic

import com.egorovsoft.vkconnector.mvp.model.basictypes.Video
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IVideoCache {
    fun putVideo(video: Video, wallId: Int): Completable
    fun getWallVideos(wallId: Int): Single<List<Video>>
    fun getFirstWallVideo(wallId: Int): Single<Video>
}