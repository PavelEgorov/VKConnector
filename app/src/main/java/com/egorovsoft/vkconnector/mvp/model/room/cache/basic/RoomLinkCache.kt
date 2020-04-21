package com.egorovsoft.vkconnector.mvp.model.room.cache.basic

import com.egorovsoft.vkconnector.mvp.model.basictypes.Link
import com.egorovsoft.vkconnector.mvp.model.room.basicroom.RoomLink
import com.egorovsoft.vkconnector.mvp.model.room.db.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomLinkCache(val database: Database): ILinkCache {
    override fun putLink(link: Link, wallId: Int)= Completable.fromAction {
        val room = database.linkDao.findByUrl(link.url)?.apply {
            this.url = link.url
            this.title = link.title
            this.description = link.description
            this.wallId = wallId
        } ?: RoomLink(link.url, wallId, link.title, link.description)

        database.linkDao.insert(room)
    }.subscribeOn(Schedulers.io())

    override fun getWallLinks(wallId: Int)= Single.fromCallable {
        return@fromCallable database.linkDao.getAllWallLink(wallId)?.run {
            this.map {
                Link(
                    it.url,
                    it.title,
                    it.description
                )
            }
        }
    }.subscribeOn(Schedulers.io())

    override fun getFirstWallLink(wallId: Int)= Single.fromCallable {
        return@fromCallable database.linkDao.getFirstWallLink(wallId)?.run {
            Link(this.url, this.title, this.description)
        }?: throw Throwable("No surch link")
    }.subscribeOn(Schedulers.io())
}