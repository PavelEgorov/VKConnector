package com.egorovsoft.vkconnector.di.friends.module

import com.egorovsoft.vkconnector.di.friends.FriendsScope
import com.egorovsoft.vkconnector.mvp.model.IVKApi
import com.egorovsoft.vkconnector.mvp.model.friends.FriendsModel
import com.egorovsoft.vkconnector.mvp.model.room.cache.token.ICurrentSessionInfoCache
import com.egorovsoft.vkconnector.mvp.model.room.cache.user.IUserCache
import dagger.Module
import dagger.Provides

@Module
class FriendsModule {
    @FriendsScope
    @Provides
    fun getFriends(api: IVKApi, roomCurrentSessionCache: ICurrentSessionInfoCache, roomUser: IUserCache): FriendsModel{
        return FriendsModel(api, roomCurrentSessionCache, roomUser)
    }
}