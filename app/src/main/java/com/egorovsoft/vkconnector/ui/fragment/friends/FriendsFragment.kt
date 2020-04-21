package com.egorovsoft.vkconnector.ui.fragment.friends

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.egorovsoft.vkconnector.R
import com.egorovsoft.vkconnector.mvp.presenter.fragment.FriendsPresenter
import com.egorovsoft.vkconnector.mvp.presenter.fragment.WallPresenter
import com.egorovsoft.vkconnector.mvp.view.fragment.friends.FriendsView
import com.egorovsoft.vkconnector.ui.MainApp
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_friends.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class FriendsFragment : MvpAppCompatFragment(), FriendsView {
    companion object{
        fun newInstance() = FriendsFragment()
    }
    @InjectPresenter lateinit var presenter: FriendsPresenter

    val friendsComponent = MainApp.instance.friendsComponent
    lateinit var adapter: FriendsRvAdapter

    @ProvidePresenter
    fun providePresenter() = FriendsPresenter(
        AndroidSchedulers.mainThread()
    ).apply {
        friendsComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_friends, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        friendsComponent.inject(this)
    }

    override fun init() {
        rv_friends.layoutManager = LinearLayoutManager(context)
        adapter = FriendsRvAdapter(presenter.rvPresenter).apply {
            friendsComponent.inject(this)
        }
        rv_friends.adapter = adapter;
    }

    override fun updateList() {
        adapter?.let {
            it.notifyDataSetChanged()
        }
    }

}
