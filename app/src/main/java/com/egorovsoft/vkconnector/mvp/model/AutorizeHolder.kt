package com.egorovsoft.vkconnector.mvp.model

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object AutorizeHolder {
    val api : IVKApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://oauth.vk.com")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        retrofit.create(IVKApi::class.java)
    }
}