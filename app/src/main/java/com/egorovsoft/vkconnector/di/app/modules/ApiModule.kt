package com.egorovsoft.vkconnector.di.app.modules

import com.egorovsoft.vkconnector.mvp.model.IVKApi
import com.egorovsoft.vkconnector.mvp.model.IVKAuthorize
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Named("baseUrl")
    @Provides
    fun baseUrl(): String {
        return "https://api.vk.com"
    }

    @Named("baseUrlLogin")
    @Provides
    fun baseUrlLogin(): String {
        return "https://oauth.vk.com"
    }

    @Singleton
    @Provides
    fun api(@Named("baseUrl") baseUrl: String, gson: Gson): IVKApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IVKApi::class.java)
    }

    @Singleton
    @Provides
    fun apiLogin(@Named("baseUrlLogin") baseUrlLogin: String): IVKAuthorize {
        return Retrofit.Builder()
            .baseUrl(baseUrlLogin)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(IVKAuthorize::class.java)
    }

    @Provides
    fun gson(): Gson {
        return GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }


//    @Singleton
//    @Provides
//    fun networkStatus(app: MainApp): NetworkStatus {
//        return AndroidNetworkStatus(app)
//    }
}