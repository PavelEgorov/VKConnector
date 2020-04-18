package com.egorovsoft.vkconnector.ui

import android.app.Application
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import timber.log.Timber

class MainApp: Application() {
    companion object{
        lateinit var instance: MainApp
    }

    val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Timber.plant(Timber.DebugTree())
    }


    fun getNavigatorHolder() = cicerone.navigatorHolder
    fun getRouter() = cicerone.router
}