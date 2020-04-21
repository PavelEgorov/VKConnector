package com.egorovsoft.vkconnector.mvp.model

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IVKAuthorize {
    @GET("/authorize")
    fun authorize(
        @Query("client_id") clientId: Int = Const.programID, // id приложения в VK // TODO: Переделать как переменную
        @Query("redirect_uri") redirectUri: String = Const.redirectUri,            // TODO: Переделать как переменную
        @Query("display") display: String = "mobile",
        @Query("scope") scope: String = "wall",
        @Query("response_type") responseType: String = "token",
        @Query("v") v: String = Const.versionAPI
    ): Single<String>
}