package com.egorovsoft.vkconnector.mvp.model.room.cache.basic

import com.egorovsoft.vkconnector.mvp.model.basictypes.Audio
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IAudioCache {
    fun putAudio(audio: Audio, wallId: Int): Completable
    fun getWallAudios(wallId: Int): Single<List<Audio>>
    fun getFirstWallAudio(wallId: Int): Single<Audio>
}