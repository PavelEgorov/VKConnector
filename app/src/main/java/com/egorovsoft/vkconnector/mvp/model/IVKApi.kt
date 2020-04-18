package com.egorovsoft.vkconnector.mvp.model

import com.egorovsoft.vkconnector.mvp.model.user.User
import com.egorovsoft.vkconnector.mvp.model.wall.Wall
import com.egorovsoft.vkconnector.mvp.model.wall.WallItem
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

//https://oauth.vk.com/blank.html#
// access_token=1c6d5b1bd3499e9f86968052994219c2f972e54f2c1f4367164bf0eb67e5a3d2d0528e43821a1d38bef71
// &expires_in=86400
// &user_id=139860951

///https://api.vk.com/method/users.get?access_token=e70cf4e2122d0ad2272449dc7a4c57289500349056d274ce6da528d068ca8aafa24ae6c9a17eb79f6e0be&user_ids=139860951&fields=photo_50&v=5.122
///https://api.vk.com/method/wall.get?access_token=e70cf4e2122d0ad2272449dc7a4c57289500349056d274ce6da528d068ca8aafa24ae6c9a17eb79f6e0be&owner_id=-139860951&count=10&v=5.122

//https://api.vk.com/method/
//https://oauth.vk.com/authorize?client_id=7383836&redirect_uri=https://oauth.vk.com/blank.html&display=mobile&scope=wall&response_type=token&v=5.122
interface IVKApi {
    @GET("/authorize")
    fun authorize(
        @Query("client_id") clientId: Int = Const.programID, // id приложения в VK // TODO: Переделать как переменную
        @Query("redirect_uri") redirectUri: String = Const.redirectUri,            // TODO: Переделать как переменную
        @Query("display") display: String = "mobile",
        @Query("scope") scope: String = "wall",
        @Query("response_type") responseType: String = "token",
        @Query("v") v: String = Const.versionAPI
    ): Single<String>

    @GET("/method/users.get")
    fun userGet(
        @Query("access_token") accessToken : String,
        @Query("user_ids") userIds: Int,
        @Query("fields") fields: String = "photo_50",
        @Query("v") v: String = Const.versionAPI
    ):Single<Responce<ArrayList<User>>>

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
        @Query("posts") posts : String,
        @Query("copy_history_depth") copyHistoryDepth : Int = 1,
        @Query("v") v: String = Const.versionAPI
    ):Single<ArrayList<WallItem>>
}