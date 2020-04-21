package com.egorovsoft.vkconnector.mvp.model.room.cache.basic

import com.egorovsoft.vkconnector.mvp.model.basictypes.Audio
import com.egorovsoft.vkconnector.mvp.model.basictypes.Doc
import com.egorovsoft.vkconnector.mvp.model.room.basicroom.RoomAudio
import com.egorovsoft.vkconnector.mvp.model.room.basicroom.RoomDoc
import com.egorovsoft.vkconnector.mvp.model.room.db.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomDocCache(val database: Database): IDocCache {
    override fun putDoc(doc: Doc, wallId: Int) = Completable.fromAction {
        val roomDoc = database.docDao.findById(doc.id)?.apply {
            this.id = doc.id
            this.title = doc.title
            this.ownerId = doc.ownerId
            this.url = doc.url
            this.wallId = wallId
        } ?: RoomDoc(doc.id, wallId, doc.ownerId, doc.title, doc.url)

        database.docDao.insert(roomDoc)
    }.subscribeOn(Schedulers.io())

    override fun getWallDocs(wallId: Int) = Single.fromCallable {
        return@fromCallable database.docDao.getAllWallDoc(wallId)?.run {
            this.map {
                Doc(
                    it.id,
                    it.ownerId,
                    it.title,
                    it.url
                )
            }
        }
    }.subscribeOn(Schedulers.io())

    override fun getFirstWallDoc(wallId: Int)= Single.fromCallable {
        return@fromCallable database.docDao.getFirstWallDoc(wallId)?.run {
            Doc(this.id, this.ownerId, this.title, this.url)
        } ?: throw Throwable("No surch doc")
    }.subscribeOn(Schedulers.io())
}