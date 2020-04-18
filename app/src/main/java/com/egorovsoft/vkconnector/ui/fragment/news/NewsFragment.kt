package com.egorovsoft.vkconnector.ui.fragment.news


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.egorovsoft.vkconnector.R
import com.egorovsoft.vkconnector.mvp.model.news.ItemNewsModel
import com.egorovsoft.vkconnector.mvp.presenter.fragment.NewsPresenter
import com.egorovsoft.vkconnector.mvp.view.fragment.news.NewsView
import com.egorovsoft.vkconnector.ui.MainApp
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_news.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class NewsFragment : MvpAppCompatFragment(), NewsView {
    companion object{
        fun newInstance() = NewsFragment()
    }

    @InjectPresenter
    lateinit var presenter: NewsPresenter

    lateinit var adapter: NewsRvAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_news, container, false)

    @ProvidePresenter
    fun providePresenter() = NewsPresenter(ItemNewsModel(), MainApp.instance.getRouter(), AndroidSchedulers.mainThread())

    override fun init() {
        rv_news.layoutManager = LinearLayoutManager(context)
        adapter = NewsRvAdapter(presenter.rvPresenter)
        rv_news.adapter = adapter;
    }

    override fun updateList() {
        adapter?.let{
            it.notifyDataSetChanged()
        }
    }
}
