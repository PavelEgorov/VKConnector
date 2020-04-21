package com.egorovsoft.vkconnector.di.app.modules

import com.egorovsoft.vkconnector.mvp.model.IVKAuthorize
import com.egorovsoft.vkconnector.mvp.model.room.cache.token.ICurrentSessionInfoCache
import com.egorovsoft.vkconnector.mvp.model.room.cache.token.RoomCurrentSessionCache
import com.egorovsoft.vkconnector.mvp.model.room.db.Database
import com.egorovsoft.vkconnector.mvp.model.token.AutorizeModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AutorizeModule {
    @Singleton
    @Provides
    fun authorize(api: IVKAuthorize): AutorizeModel {
        return AutorizeModel(api)
    }

    @Singleton
    @Provides
    fun cache(database: Database): ICurrentSessionInfoCache {
        return RoomCurrentSessionCache(database)
    }
}