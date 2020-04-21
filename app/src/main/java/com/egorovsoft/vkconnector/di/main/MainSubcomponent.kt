package com.egorovsoft.vkconnector.di.main

import com.egorovsoft.vkconnector.di.friends.FriendsSubcomponent
import com.egorovsoft.vkconnector.di.main.modules.UserModule
import com.egorovsoft.vkconnector.di.news.NewsSubcomponent
import com.egorovsoft.vkconnector.di.wall.WallSubcomponent
import com.egorovsoft.vkconnector.mvp.presenter.MainPresenter
import com.egorovsoft.vkconnector.ui.activity.MainActivity
import dagger.Subcomponent

@MainScope
@Subcomponent(
    modules = [
        UserModule::class
    ]
)
interface MainSubcomponent {
    fun wallSubcomponent(): WallSubcomponent
    fun newsSubcomponent(): NewsSubcomponent
    fun friendsSubcomponent(): FriendsSubcomponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
}