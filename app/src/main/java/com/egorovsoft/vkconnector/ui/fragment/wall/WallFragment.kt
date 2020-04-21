package com.egorovsoft.vkconnector.ui.fragment.wall

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager

import com.egorovsoft.vkconnector.R
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
        fun newInstance() = WallFragment()
    }

    @InjectPresenter
    lateinit var presenter: WallPresenter

    private val wallComponent = MainApp.instance.wallComponent

    @ProvidePresenter
    fun providePresenter() = WallPresenter(
        AndroidSchedulers.mainThread()
    ).apply {
        wallComponent.inject(this)
    }

    lateinit var adapter: WallRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wall, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wallComponent.inject(this)
    }
    override fun init() {
        rv_wall.layoutManager = LinearLayoutManager(context)
        adapter = WallRvAdapter(presenter.rvPresenter.apply {
            wallComponent.inject(this)
        }).apply {
            wallComponent.inject(this)
        }
        rv_wall.adapter = adapter;
    }

    override fun updateList() {
        adapter?.let{
            it.notifyDataSetChanged()
        }
    }
}
