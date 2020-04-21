package com.egorovsoft.vkconnector.ui

import android.app.Application
import com.egorovsoft.vkconnector.di.app.AppComponent
import com.egorovsoft.vkconnector.di.app.DaggerAppComponent
import com.egorovsoft.vkconnector.di.app.modules.AppModule
import com.egorovsoft.vkconnector.di.conversation.ConversationSubcomponent
import com.egorovsoft.vkconnector.di.friends.FriendsSubcomponent
import com.egorovsoft.vkconnector.di.main.MainSubcomponent
import com.egorovsoft.vkconnector.di.news.NewsSubcomponent
import com.egorovsoft.vkconnector.di.wall.WallSubcomponent
import timber.log.Timber

class MainApp: Application() {
    companion object{
        lateinit var instance: MainApp
    }

    lateinit var appComponent: AppComponent
        private set

    private var tMainComponent: MainSubcomponent? = null
    private var tWallComponent: WallSubcomponent? = null
    private var tFriendsComponent: FriendsSubcomponent? = null
    private var tConversationComponent: ConversationSubcomponent? = null

    val mainComponent: MainSubcomponent
        get() = appComponent.mainSubcomponent().also {
            tMainComponent = it
        }

    val wallComponent: WallSubcomponent
        get() = tMainComponent!!.wallSubcomponent().also {
            tWallComponent = it
        }
    val friendsComponent: FriendsSubcomponent
        get() = tMainComponent!!.friendsSubcomponent().also {
            tFriendsComponent = it
        }
    val conversationComponent: ConversationSubcomponent
        get() = tMainComponent!!.conversationSubcomponent().also {
            tConversationComponent = it
        }

    val newsComponent: NewsSubcomponent
        get() = tMainComponent!!.newsSubcomponent()


    override fun onCreate() {
        super.onCreate()
        instance = this
        Timber.plant(Timber.DebugTree())

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}