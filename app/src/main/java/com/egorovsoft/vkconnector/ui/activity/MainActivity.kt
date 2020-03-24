package com.egorovsoft.vkconnector.ui.activity

import android.os.Bundle
import com.egorovsoft.vkconnector.R
import com.egorovsoft.vkconnector.mvp.presenter.MainPresenter
import com.egorovsoft.vkconnector.mvp.view.MainView
import com.egorovsoft.vkconnector.ui.App
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter() = MainPresenter(App.instance.getRouter())

    private val navigator = SupportAppNavigator(this, R.id.conteiner)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.getNavigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.getNavigatorHolder().removeNavigator()
    }
}
