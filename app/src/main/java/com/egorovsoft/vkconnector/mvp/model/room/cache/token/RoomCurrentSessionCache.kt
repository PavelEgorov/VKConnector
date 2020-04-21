package com.egorovsoft.vkconnector.mvp.model.room.cache.token

import com.egorovsoft.vkconnector.mvp.model.room.RoomCurrentSessionInfo
import com.egorovsoft.vkconnector.mvp.model.room.db.Database
import com.egorovsoft.vkconnector.mvp.model.token.CurrentSessionInfo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomCurrentSessionCache(val database: Database): ICurrentSessionInfoCache {
    override fun getCurrentSessionInfo(appId: Int) = Single.fromCallable {
        return@fromCallable database.currentSessionInfoDao.getCurrentSession()?.run {
            CurrentSessionInfo(this.appId, this.userId, this.currentToken, this.date)
        } ?: CurrentSessionInfo(appId, 0, "", 0)
    }.subscribeOn(Schedulers.io())

    override fun putCurrentSessionInfo(currentSessionInfo: CurrentSessionInfo)= Completable.fromAction {
        val roomCurrentSessionInfo = database.currentSessionInfoDao.getCurrentSession()?.apply {
            this.appId = currentSessionInfo.appId
            this.currentToken = currentSessionInfo.currentToken
            this.userId = currentSessionInfo.userId
            this.date = currentSessionInfo.date
        } ?: RoomCurrentSessionInfo(currentSessionInfo.appId, currentSessionInfo.userId, currentSessionInfo.currentToken, currentSessionInfo.date)
        database.currentSessionInfoDao.insert(roomCurrentSessionInfo)
    }.subscribeOn(Schedulers.io())
}