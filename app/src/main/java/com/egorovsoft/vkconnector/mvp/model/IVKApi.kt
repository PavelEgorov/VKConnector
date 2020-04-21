package com.egorovsoft.vkconnector.mvp.model

import com.egorovsoft.vkconnector.mvp.model.conversation.ResponceConversations
import com.egorovsoft.vkconnector.mvp.model.conversation.messages.Messages
import com.egorovsoft.vkconnector.mvp.model.friends.ResponceFriends
import com.egorovsoft.vkconnector.mvp.model.user.User
import com.egorovsoft.vkconnector.mvp.model.wall.Wall
import com.egorovsoft.vkconnector.mvp.model.wall.WallItem
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IVKApi {
    @GET("/method/users.get")
    fun userGet(
        @Query("access_token") accessToken : String,
        @Query("user_ids") userIds: Int,
        @Query("fields") fields: String = "photo_50",
        @Query("v") v: String = Const.versionAPI
    ):Single<Responce<ArrayList<User>?>>

    @GET("/method/wall.get")
    fun wallGet(
        @Query("access_token") accessToken : String,
        @Query("owner_id") ownerId : Int, /// Должен быть отрицательным
        @Query("count") count : Int = 10, /// максимум 100
        @Query("v") v: String = Const.versionAPI
    ):Single<Responce<Wall>>

    @GET("/method/wall.getById")
    fun getById(
        @Query("access_token") accessToken : String,
        @Query("user_id") userId : Int, /// идентификатор пользователя
        @Query("posts") posts : String,
        @Query("copy_history_depth") copyHistoryDepth : Int = 1,
        @Query("v") v: String = Const.versionAPI
    ):Single<ArrayList<WallItem>>

    @GET("/method/friends.get")
    fun friendsGet(
        @Query("access_token") accessToken : String,
        @Query("user_id") userId : Int, /// идентификатор пользователя
        @Query("order") order : String = "name", /// порядок, в котором нужно вернуть список друзей
        @Query("fields") fields : String = "photo_50", /// При использовании параметра fields возвращает список объектов, иначе только id
        @Query("v") v: String = Const.versionAPI
    ):Single<Responce<ResponceFriends>>

    @GET("/method/messages.getConversations")
    fun getConversations(
        @Query("access_token") accessToken : String,
        @Query("filter") filter : String = "all",
        @Query("v") v: String = Const.versionAPI
    ):Single<Responce<ResponceConversations>>

    @GET("/method/messages.getHistory")
    fun getMessages(
        @Query("access_token") accessToken : String,
        @Query("peer_id") peerId : Int,
        @Query("v") v: String = Const.versionAPI
    ):Single<Responce<Messages>>
}