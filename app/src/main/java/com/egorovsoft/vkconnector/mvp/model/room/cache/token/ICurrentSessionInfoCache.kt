package com.egorovsoft.vkconnector.mvp.model.room.cache.token

import com.egorovsoft.vkconnector.mvp.model.token.CurrentSessionInfo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface ICurrentSessionInfoCache {
    fun getCurrentSessionInfo(appId: Int) : Single<CurrentSessionInfo>
    fun putCurrentSessionInfo(currentSessionInfo: CurrentSessionInfo): Completable
}