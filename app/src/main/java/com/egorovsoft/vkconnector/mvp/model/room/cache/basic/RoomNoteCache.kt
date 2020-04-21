package com.egorovsoft.vkconnector.mvp.model.room.cache.basic

import com.egorovsoft.vkconnector.mvp.model.basictypes.Note
import com.egorovsoft.vkconnector.mvp.model.room.basicroom.RoomNote
import com.egorovsoft.vkconnector.mvp.model.room.db.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomNoteCache(val database: Database): INoteCache {
    override fun putNote(note: Note, wallId: Int)= Completable.fromAction {
        val room = database.noteDao.findById(note.id)?.apply {
            this.id = note.id
            this.title = note.title
            this.text = note.text
            this.wallId = wallId
        } ?: RoomNote(note.id, wallId, note.ownerId, note.title,note.text)

        database.noteDao.insert(room)
    }.subscribeOn(Schedulers.io())

    override fun getWallNotes(wallId: Int)= Single.fromCallable {
        return@fromCallable database.noteDao.getAllWallNote(wallId)?.run {
            this.map {
                Note(
                    it.id,
                    it.ownerId,
                    it.title,
                    it.text
                )
            }
        }
    }.subscribeOn(Schedulers.io())

    override fun getFirstWallNote(wallId: Int)= Single.fromCallable {
        return@fromCallable database.noteDao.getFirstWallNote(wallId)?.run {
            Note(this.id, this.ownerId, this.title, this.text)
        }?: throw Throwable("No surch note")
    }.subscribeOn(Schedulers.io())
}