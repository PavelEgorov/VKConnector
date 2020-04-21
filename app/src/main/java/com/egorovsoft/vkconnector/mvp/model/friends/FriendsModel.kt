package com.egorovsoft.vkconnector.mvp.model.friends

import com.egorovsoft.vkconnector.mvp.model.Const
import com.egorovsoft.vkconnector.mvp.model.IVKApi
import com.egorovsoft.vkconnector.mvp.model.room.cache.token.ICurrentSessionInfoCache
import com.egorovsoft.vkconnector.mvp.model.room.cache.user.IUserCache
import io.reactivex.rxjava3.schedulers.Schedulers

class FriendsModel(
    val api: IVKApi,
    val currentSessionInfo: ICurrentSessionInfoCache,
    val roomUser:IUserCache
) {
    fun getFriends() = currentSessionInfo.getCurrentSessionInfo(Const.programID)
        .flatMap {
            api.friendsGet(it.currentToken, it.userId)
                .flatMap {
                    it.response?.let {
                        it.items?.let {
                            roomUser.putUsers(it).toSingleDefault(it)
                        }
                    }
                }
        }.subscribeOn(Schedulers.io())
}