package com.egorovsoft.vkconnector.di.wall

import com.egorovsoft.vkconnector.di.wall.modules.WallModule
import com.egorovsoft.vkconnector.mvp.presenter.fragment.WallPresenter
import com.egorovsoft.vkconnector.ui.fragment.wall.WallFragment
import com.egorovsoft.vkconnector.ui.fragment.wall.WallRvAdapter
import dagger.Subcomponent

@WallScope
@Subcomponent(
    modules = [
        WallModule::class
    ]
)
interface WallSubcomponent {
    fun inject(wallFragment: WallFragment)
    fun inject(wallPresenter: WallPresenter)
    fun inject(rvPresenter: WallPresenter.RvPresenter)

    fun inject(wallRvAdapter: WallRvAdapter)
}