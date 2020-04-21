package com.egorovsoft.vkconnector.di.news

import com.egorovsoft.vkconnector.di.news.modules.NewsModule
import com.egorovsoft.vkconnector.mvp.presenter.fragment.NewsPresenter
import com.egorovsoft.vkconnector.ui.fragment.news.NewsFragment
import com.egorovsoft.vkconnector.ui.fragment.news.NewsRvAdapter
import dagger.Subcomponent

@NewsScope
@Subcomponent(
    modules = [
        NewsModule::class
    ]
)
interface NewsSubcomponent {
    fun inject(newsFragment: NewsFragment)
    fun inject(newsPresenter: NewsPresenter)

    fun inject(newsRvAdapter: NewsRvAdapter)
}