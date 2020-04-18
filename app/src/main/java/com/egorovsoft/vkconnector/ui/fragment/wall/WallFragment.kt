package com.egorovsoft.vkconnector.ui.fragment.wall

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager

import com.egorovsoft.vkconnector.R
import com.egorovsoft.vkconnector.mvp.model.ApiHolder
import com.egorovsoft.vkconnector.mvp.model.user.UserModel
import com.egorovsoft.vkconnector.mvp.model.wall.WallModel
import com.egorovsoft.vkconnector.mvp.presenter.fragment.WallPresenter
import com.egorovsoft.vkconnector.mvp.view.fragment.wall.WallView
import com.egorovsoft.vkconnector.ui.MainApp
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_wall.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class WallFragment : MvpAppCompatFragment(), WallView {
    companion object{
        private val KEY_TOKEN = WallFragment::class.java.name + "extra.TOKEN"
        private val KEY_USER_ID = WallFragment::class.java.name + "extra.USERID"

        fun newInstance(token: String, userId: Int) = WallFragment().apply {
            arguments = bundleOf(KEY_TOKEN to token, KEY_USER_ID to userId)
        }
    }

    @InjectPresenter
    lateinit var presenter: WallPresenter

    @ProvidePresenter
    fun providePresenter() = WallPresenter(
        MainApp.instance.getRouter(),
        AndroidSchedulers.mainThread(),
        WallModel(ApiHolder.api),
        UserModel(ApiHolder.api)
    ).apply {
        val bundle = arguments
        bundle?.let {
            WallPresenter@this.initData(bundle.getString(KEY_TOKEN), bundle.getInt(KEY_USER_ID))
        }
    }

    lateinit var adapter: WallRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wall, container, false)
    }

    override fun init() {
        rv_wall.layoutManager = LinearLayoutManager(context)
        adapter = WallRvAdapter(presenter.rvPresenter)
        rv_wall.adapter = adapter;
    }

    override fun updateList() {
        adapter?.let{
            it.notifyDataSetChanged()
        }
    }
}
