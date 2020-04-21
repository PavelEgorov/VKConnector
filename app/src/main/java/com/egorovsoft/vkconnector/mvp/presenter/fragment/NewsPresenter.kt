package com.egorovsoft.vkconnector.mvp.presenter.fragment

import com.egorovsoft.vkconnector.mvp.model.news.ItemNews
import com.egorovsoft.vkconnector.mvp.model.news.ItemNewsModel
import com.egorovsoft.vkconnector.mvp.view.fragment.news.IRvNewsPresenter
import com.egorovsoft.vkconnector.mvp.view.fragment.news.IViewHolderNews
import com.egorovsoft.vkconnector.mvp.view.fragment.news.NewsView
import com.egorovsoft.vkconnector.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class NewsPresenter(val newsList: ItemNewsModel, val mainThread: Scheduler) : MvpPresenter<NewsView>(){

    @Inject lateinit var router: Router

    class RvPresenter() : IRvNewsPresenter {
        val news = mutableListOf<ItemNews>()

        override var onClickListener: ((IViewHolderNews) -> Unit)? = null

        override fun bindViewHolder(holder: IViewHolderNews) {
            holder.setText(news[holder.pos].text);
        }

        override fun getItemCount(): Int = news.size

    }

    val rvPresenter = RvPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        updateNews()

        rvPresenter.onClickListener = { itemView ->
            router.navigateTo(Screens.AttachmentsScreen(rvPresenter.news[itemView.pos].attachments))
        }
    }

    fun updateNews(){
        /// Меняем обновление
        newsList.getNews()
            .subscribeOn(Schedulers.io())
            .observeOn(mainThread)
            .subscribe{
                rvPresenter.news.clear()
                rvPresenter.news.addAll(it)
                viewState.updateList()
            }
    }
}