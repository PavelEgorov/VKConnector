package com.egorovsoft.vkconnector.mvp.model.conversation

import com.egorovsoft.vkconnector.mvp.model.Const
import com.egorovsoft.vkconnector.mvp.model.IVKApi
import com.egorovsoft.vkconnector.mvp.model.room.cache.token.ICurrentSessionInfoCache
import io.reactivex.rxjava3.schedulers.Schedulers

class ConversationModel(val api: IVKApi, val currentSessionInfo: ICurrentSessionInfoCache) {
    fun getConversations() = currentSessionInfo.getCurrentSessionInfo(Const.programID)
        .flatMap {
            api.getConversations(it.currentToken)
                .map {
                    it.response?.let {
                        it.item?.let {
                            it
                        }
                    }
                }
        }.subscribeOn(Schedulers.io())
}