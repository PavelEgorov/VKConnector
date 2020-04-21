package com.egorovsoft.vkconnector.ui.fragment.friends

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.egorovsoft.vkconnector.R
import com.egorovsoft.vkconnector.mvp.presenter.fragment.FriendsPresenter
import com.egorovsoft.vkconnector.mvp.presenter.fragment.WallPresenter
import com.egorovsoft.vkconnector.mvp.view.fragment.friends.FriendsView
import com.egorovsoft.vkconnector.ui.MainApp
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
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

    override fun init() {

    }

    override fun updateList() {

    }

}
