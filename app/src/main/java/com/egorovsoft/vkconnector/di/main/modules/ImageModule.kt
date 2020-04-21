package com.egorovsoft.vkconnector.di.main.modules

import android.widget.ImageView
import com.egorovsoft.vkconnector.di.main.MainScope
import com.egorovsoft.vkconnector.mvp.model.image.IImageLoader
import com.egorovsoft.vkconnector.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides

@Module
class ImageModule {
    @MainScope
    @Provides
    fun imageLoader(): IImageLoader<ImageView>{
        return GlideImageLoader()
    }
}