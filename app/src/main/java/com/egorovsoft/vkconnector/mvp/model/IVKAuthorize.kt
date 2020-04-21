package com.egorovsoft.vkconnector.mvp.model

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IVKAuthorize {
    //TODO: запрос на получение ключа для диалогов был отправлен, дописать сообщения, когда будет ответ.
    @GET("/authorize")
    fun authorize(
        @Query("client_id") clientId: Int = Const.programID, // id приложения в VK
        @Query("redirect_uri") redirectUri: String = Const.redirectUri,
        @Query("display") display: String = "mobile",
        @Query("scope") scope: String = "wall",// "messages" к сожалению получить доступ к сообщениям можно только после модеризации ВК.
        @Query("response_type") responseType: String = "token",
        @Query("v") v: String = Const.versionAPI
    ): Single<String>
}