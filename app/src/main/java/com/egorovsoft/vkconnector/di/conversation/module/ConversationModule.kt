package com.egorovsoft.vkconnector.di.conversation.module

import com.egorovsoft.vkconnector.di.conversation.ConversationScope
import com.egorovsoft.vkconnector.mvp.model.IVKApi
import com.egorovsoft.vkconnector.mvp.model.conversation.ConversationModel
import com.egorovsoft.vkconnector.mvp.model.room.cache.token.ICurrentSessionInfoCache
import com.egorovsoft.vkconnector.mvp.model.room.cache.token.RoomCurrentSessionCache
import dagger.Module
import dagger.Provides

@Module
class ConversationModule {
    @ConversationScope
    @Provides
    fun conversationModel(api: IVKApi, roomCurrentSessionCache: ICurrentSessionInfoCache): ConversationModel{
        return ConversationModel(api, roomCurrentSessionCache)
    }
}