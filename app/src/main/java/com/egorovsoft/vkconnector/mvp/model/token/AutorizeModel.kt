package com.egorovsoft.vkconnector.mvp.model.token

import com.egorovsoft.vkconnector.mvp.model.IVKApi
import io.reactivex.rxjava3.schedulers.Schedulers

class AutorizeModel(val api: IVKApi) {
    fun authorize() = api.authorize().subscribeOn(Schedulers.io())
}