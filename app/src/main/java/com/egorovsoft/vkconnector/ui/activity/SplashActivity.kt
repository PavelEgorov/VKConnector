package com.egorovsoft.vkconnector.ui.activity

import android.os.Bundle
import android.view.View
import com.egorovsoft.vkconnector.R
import com.egorovsoft.vkconnector.mvp.presenter.SplashPresenter
import com.egorovsoft.vkconnector.mvp.view.SplashView
import com.egorovsoft.vkconnector.ui.MainApp
import com.egorovsoft.vkconnector.ui.webview.VKWebViewClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_splash.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class SplashActivity : MvpAppCompatActivity(), SplashView {

    @InjectPresenter
    lateinit var presenter: SplashPresenter

    @ProvidePresenter
    fun providePresenter() = SplashPresenter(
        AndroidSchedulers.mainThread()
    ).apply {
        MainApp.instance.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        MainApp.instance.appComponent.inject(this)
    }

    override fun showAuthorization(page: String) {
        webview_auth.visibility = View.VISIBLE

        webview_auth.settings.javaScriptEnabled = true
        webview_auth.loadData(page, null, null)
        webview_auth.webViewClient = VKWebViewClient(presenter)
    }

    override fun hideAuthorization() {
        webview_auth.visibility = View.GONE
    }

    override fun loadUrl(url: String) {
        webview_auth.loadUrl(url)
    }

    override fun openMainScreen() {
        MainActivity.start(this)
    }
}
