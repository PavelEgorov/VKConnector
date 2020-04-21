package com.egorovsoft.vkconnector.di.app.modules

import com.egorovsoft.vkconnector.ui.MainApp
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: MainApp) {
    @Provides
    fun app():MainApp{
        return app
    }
}