package com.egorovsoft.vkconnector.mvp.model.room.cache.basic

import com.egorovsoft.vkconnector.mvp.model.basictypes.Link
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface ILinkCache {
    fun putLink(link: Link, wallId: Int): Completable
    fun getWallLinks(wallId: Int): Single<List<Link>>
    fun getFirstWallLink(wallId: Int): Single<Link>
}