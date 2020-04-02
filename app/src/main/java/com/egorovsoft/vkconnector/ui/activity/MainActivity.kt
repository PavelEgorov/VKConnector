package com.egorovsoft.vkconnector.ui.activity

import android.os.Bundle
import com.egorovsoft.vkconnector.R
import com.egorovsoft.vkconnector.mvp.model.ApiHolder
import com.egorovsoft.vkconnector.mvp.model.AutorizeHolder
import com.egorovsoft.vkconnector.mvp.model.token.AutorizeModel
import com.egorovsoft.vkconnector.mvp.model.user.UserModel
import com.egorovsoft.vkconnector.mvp.presenter.MainPresenter
import com.egorovsoft.vkconnector.mvp.view.MainView
import com.egorovsoft.vkconnector.ui.App
import com.egorovsoft.vkconnector.ui.image.GlideImageLoader
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
        App.instance.getRouter(),
        AndroidSchedulers.mainThread(),
        AutorizeModel(AutorizeHolder.api),
        UserModel(ApiHolder.api)
    )

    private val navigator = SupportAppNavigator(this, R.id.conteiner)
    private val imageLoader = GlideImageLoader()

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

    override fun setUserName(txt: String) {
        tv_username.text = txt
    }

    override fun loadPhoto(path: String) {
        imageLoader.loadInto(path, iv_photo)
    }
}
