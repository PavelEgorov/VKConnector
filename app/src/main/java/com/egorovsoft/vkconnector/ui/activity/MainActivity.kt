package com.egorovsoft.vkconnector.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import com.egorovsoft.vkconnector.R
import com.egorovsoft.vkconnector.mvp.model.image.IImageLoader
import com.egorovsoft.vkconnector.mvp.presenter.MainPresenter
import com.egorovsoft.vkconnector.mvp.view.MainView
import com.egorovsoft.vkconnector.ui.MainApp
import com.egorovsoft.vkconnector.ui.image.GlideImageLoader
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {
    companion object{
        fun start(context: Context) = Intent(context, MainActivity::class.java).apply {
            context.startActivity(this)
        }
    }

    @Inject lateinit var navigatorHolder: NavigatorHolder
    @Inject lateinit var imageLoader: IImageLoader<ImageView>

    @InjectPresenter
    lateinit var presenter: MainPresenter

    private val mainComponent = MainApp.instance.mainComponent

    @ProvidePresenter
    fun providePresenter() = MainPresenter(
        AndroidSchedulers.mainThread()
    ).apply { mainComponent.inject(this) }

    private val navigator = SupportAppNavigator(this, R.id.conteiner)
    private val navigationListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when(it.itemId){
            R.id.menu_wall -> {
                presenter.menuWallSelected()
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_friends -> {
                presenter.menuFriendselected()
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

        mainComponent.inject(this)

        bnv_main.setOnNavigationItemSelectedListener(navigationListener)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun setUserName(txt: String) {
        tv_username.text = txt
    }

    override fun loadPhoto(path: String) {
        imageLoader.loadInto(path, iv_photo)
    }
}
