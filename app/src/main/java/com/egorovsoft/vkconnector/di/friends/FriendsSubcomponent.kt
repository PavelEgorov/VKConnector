package com.egorovsoft.vkconnector.di.friends

import com.egorovsoft.vkconnector.mvp.presenter.fragment.FriendsPresenter
import com.egorovsoft.vkconnector.ui.fragment.friends.FriendsFragment
import dagger.Subcomponent

@FriendsScope
@Subcomponent(
    modules = [

    ]
)
interface FriendsSubcomponent {
    fun inject(friendsFragment: FriendsFragment)
    fun inject(friendsPresenter: FriendsPresenter)
}