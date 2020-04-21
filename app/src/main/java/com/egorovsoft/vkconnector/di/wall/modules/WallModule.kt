package com.egorovsoft.vkconnector.di.wall.modules

import com.egorovsoft.vkconnector.di.wall.WallScope
import com.egorovsoft.vkconnector.mvp.model.IVKApi
import com.egorovsoft.vkconnector.mvp.model.room.cache.basic.*
import com.egorovsoft.vkconnector.mvp.model.room.cache.wall.IWallCache
import com.egorovsoft.vkconnector.mvp.model.room.cache.wall.RoomWallCache
import com.egorovsoft.vkconnector.mvp.model.room.db.Database
import com.egorovsoft.vkconnector.mvp.model.wall.WallModel
import dagger.Module
import dagger.Provides

@Module
class WallModule {
    @WallScope
    @Provides
    fun wall(api: IVKApi,
             wallCache: IWallCache,
             photoCache: IPhotoCache,
             sizeCache: ISizesCache,
             audioCache: IAudioCache,
             docCache: IDocCache,
             linkCache: ILinkCache,
             noteCache: INoteCache,
             videoCache: IVideoCache
    ): WallModel{
        return WallModel(api,
            wallCache,
            photoCache,
            sizeCache,
            audioCache,
            docCache,
            linkCache,
            noteCache,
            videoCache
        )
    }

    @WallScope
    @Provides
    fun cache(database: Database): IWallCache{
        return RoomWallCache(database)
    }
}