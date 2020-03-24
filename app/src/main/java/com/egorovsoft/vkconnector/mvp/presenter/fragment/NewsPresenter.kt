package com.egorovsoft.vkconnector.mvp.presenter.fragment

import com.egorovsoft.vkconnector.mvp.model.news.ItemNews
import com.egorovsoft.vkconnector.mvp.model.news.ItemNewsModel
import com.egorovsoft.vkconnector.mvp.view.fragment.news.IRvNewsPresenter
import com.egorovsoft.vkconnector.mvp.view.fragment.news.IViewHolderNews
import com.egorovsoft.vkconnector.mvp.view.fragment.news.NewsView
import com.egorovsoft.vkconnector.navigation.Screens
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

@InjectViewState
class NewsPresenter(val newsList: ItemNewsModel, val router: Router) : MvpPresenter<NewsView>(){

    class RvPresenter(val news: MutableList<ItemNews>) : IRvNewsPresenter {
        override var onClickListener: ((IViewHolderNews) -> Unit)? = null

        override fun bindViewHolder(holder: IViewHolderNews) {
            holder.setText(news[holder.pos].text);
        }

        override fun getItemCount(): Int = news.size

    }

    val rvPresenter = RvPresenter(newsList.getNews())

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

        rvPresenter.onClickListener = { itemView ->
            router.navigateTo(Screens.AttachmentsScreen(rvPresenter.news[itemView.pos].attachments))
        }
    }

    fun updateNews(){
        newsList.getNews().let {
            rvPresenter.news.clear()
            rvPresenter.news.addAll(it)
            viewState.updateList()
        }
    }
}