package com.egorovsoft.vkconnector.di.friends

import com.egorovsoft.vkconnector.di.friends.module.FriendsModule
import com.egorovsoft.vkconnector.mvp.presenter.fragment.FriendsPresenter
import com.egorovsoft.vkconnector.ui.fragment.friends.FriendsFragment
import com.egorovsoft.vkconnector.ui.fragment.friends.FriendsRvAdapter
import dagger.Subcomponent

@FriendsScope
@Subcomponent(
    modules = [
        FriendsModule::class
    ]
)
interface FriendsSubcomponent {
    fun inject(friendsFragment: FriendsFragment)
    fun inject(friendsPresenter: FriendsPresenter)

    fun inject(friendsRvAdapter: FriendsRvAdapter)
    fun inject(viewHolder: FriendsRvAdapter.ViewHolder)
}