package com.egorovsoft.vkconnector.mvp.model.room.cache.basic

import com.egorovsoft.vkconnector.mvp.model.basictypes.Doc
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IDocCache {
    fun putDoc(doc: Doc, wallId: Int): Completable
    fun getWallDocs(wallId: Int): Single<List<Doc>>
    fun getFirstWallDoc(wallId: Int): Single<Doc>
}