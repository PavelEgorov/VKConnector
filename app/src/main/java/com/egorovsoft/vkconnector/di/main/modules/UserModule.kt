package com.egorovsoft.vkconnector.di.main.modules

import com.egorovsoft.vkconnector.di.main.MainScope
import com.egorovsoft.vkconnector.mvp.model.IVKApi
import com.egorovsoft.vkconnector.mvp.model.room.cache.basic.*
import com.egorovsoft.vkconnector.mvp.model.room.cache.user.IUserCache
import com.egorovsoft.vkconnector.mvp.model.room.cache.user.RoomUserCache
import com.egorovsoft.vkconnector.mvp.model.room.db.Database
import com.egorovsoft.vkconnector.mvp.model.user.UserModel
import dagger.Module
import dagger.Provides

@Module
class UserModule {
    @MainScope
    @Provides
    fun users(api: IVKApi, userCache: IUserCache): UserModel{
        return UserModel(api, userCache)
    }

    @MainScope
    @Provides
    fun cache(database: Database): IUserCache{
        return RoomUserCache(database)
    }

    @MainScope
    @Provides
    fun audio(database: Database): IAudioCache{
        return RoomAudioCache(database)
    }

    @MainScope
    @Provides
    fun doc(database: Database): IDocCache {
        return RoomDocCache(database)
    }

    @MainScope
    @Provides
    fun link(database: Database): ILinkCache {
        return RoomLinkCache(database)
    }

    @MainScope
    @Provides
    fun note(database: Database): INoteCache {
        return RoomNoteCache(database)
    }

    @MainScope
    @Provides
    fun photo(database: Database): IPhotoCache {
        return RoomPhotoCache(database)
    }

    @MainScope
    @Provides
    fun sizes(database: Database): ISizesCache {
        return RoomSizesCache(database)
    }

    @MainScope
    @Provides
    fun video(database: Database): IVideoCache {
        return RoomVideoCache(database)
    }
}