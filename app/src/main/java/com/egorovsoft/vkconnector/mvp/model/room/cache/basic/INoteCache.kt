package com.egorovsoft.vkconnector.mvp.model.room.cache.basic

import com.egorovsoft.vkconnector.mvp.model.basictypes.Note
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface INoteCache {
    fun putNote(note: Note, wallId: Int): Completable
    fun getWallNotes(wallId: Int): Single<List<Note>>
    fun getFirstWallNote(wallId: Int): Single<Note>
}