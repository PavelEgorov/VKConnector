package com.egorovsoft.vkconnector.mvp.model.room.cache.basic

import com.egorovsoft.vkconnector.mvp.model.basictypes.Audio
import com.egorovsoft.vkconnector.mvp.model.room.basicroom.RoomAudio
import com.egorovsoft.vkconnector.mvp.model.room.db.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomAudioCache(val database: Database): IAudioCache {
    override fun putAudio(audio: Audio, wallId: Int) = Completable.fromAction {
        val roomAudio = database.audioDao.findById(audio.id)?.apply {
            this.id = audio.id
            this.title = audio.title
            this.artist = audio.artist
            this.ownerId = audio.ownerId
            this.url = audio.url
            this.wallId = wallId
        } ?: RoomAudio(audio.id, wallId, audio.ownerId, audio.artist, audio.title, audio.url)

        database.audioDao.insert(roomAudio)
    }.subscribeOn(Schedulers.io())

    override fun getWallAudios(wallId: Int) = Single.fromCallable {
        return@fromCallable database.audioDao.getAllWallAudio(wallId)?.run {
            this.map {
                Audio(it.id, it.ownerId, it.artist, it.title, it.url)
            }
        }
    }.subscribeOn(Schedulers.io())


    override fun getFirstWallAudio(wallId: Int)= Single.fromCallable {
        return@fromCallable database.audioDao.getFirstWallAudio(wallId)?.run {
            Audio(this.id, this.ownerId, this.artist, this.title, this.url)
        }?: throw Throwable("No surch audio")
    }.subscribeOn(Schedulers.io())
}