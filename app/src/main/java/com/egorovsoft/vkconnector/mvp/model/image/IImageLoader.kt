package com.egorovsoft.vkconnector.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}