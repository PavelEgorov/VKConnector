package com.egorovsoft.vkconnector.di.app

import com.egorovsoft.vkconnector.di.app.modules.*
import com.egorovsoft.vkconnector.di.main.MainSubcomponent
import com.egorovsoft.vkconnector.mvp.presenter.SplashPresenter
import com.egorovsoft.vkconnector.ui.activity.SplashActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        DatabaseModule::class,
        ApiModule::class,
        AutorizeModule::class
    ]
)
interface AppComponent {
    fun mainSubcomponent(): MainSubcomponent

    fun inject(splashActivity: SplashActivity)
    fun inject(splashPresenter: SplashPresenter)
}