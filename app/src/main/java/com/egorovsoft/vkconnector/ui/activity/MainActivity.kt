package com.egorovsoft.vkconnector.ui.activity

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import com.egorovsoft.vkconnector.R
import com.egorovsoft.vkconnector.mvp.model.ApiHolder
import com.egorovsoft.vkconnector.mvp.model.AutorizeHolder
import com.egorovsoft.vkconnector.mvp.model.token.AutorizeModel
import com.egorovsoft.vkconnector.mvp.model.user.UserModel
import com.egorovsoft.vkconnector.mvp.presenter.MainPresenter
import com.egorovsoft.vkconnector.mvp.view.MainView
import com.egorovsoft.vkconnector.ui.MainApp
import com.egorovsoft.vkconnector.ui.image.GlideImageLoader
import com.egorovsoft.vkconnector.ui.webview.VKWebViewClient
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter() = MainPresenter(
        MainApp.instance.getRouter(),
        AndroidSchedulers.mainThread(),
        AutorizeModel(AutorizeHolder.api),
        UserModel(ApiHolder.api)
    )

    private val navigator = SupportAppNavigator(this, R.id.conteiner)
    private val imageLoader = GlideImageLoader()
    private val navigationListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when(it.itemId){
            R.id.menu_wall -> {
                presenter.menuWallSelected()
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_news -> {
                presenter.menuNewsSelected()
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_message -> {
                presenter.menuMessageSelected()
                return@OnNavigationItemSelectedListener true
            }
        }
        return@OnNavigationItemSelectedListener false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bnv_main.setOnNavigationItemSelectedListener(navigationListener)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        MainApp.instance.getNavigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        MainApp.instance.getNavigatorHolder().removeNavigator()
    }

    override fun showAuthorization(page: String) {
        webview_auth.visibility = VISIBLE

        webview_auth.settings.javaScriptEnabled = true
        webview_auth.loadData(page, null, null)
        webview_auth.webViewClient = VKWebViewClient(presenter)
    }

    override fun hideAuthorization() {
        webview_auth.visibility = GONE
    }

    override fun loadUrl(url: String) {
        webview_auth.loadUrl(url)
    }

    override fun setUserName(txt: String) {
        tv_username.text = txt
    }

    override fun loadPhoto(path: String) {
        imageLoader.loadInto(path, iv_photo)
    }
}
