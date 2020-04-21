package com.egorovsoft.vkconnector.mvp.model.token

import com.egorovsoft.vkconnector.mvp.model.IVKAuthorize
import io.reactivex.rxjava3.schedulers.Schedulers

class AutorizeModel(val api: IVKAuthorize) {
    fun authorize() = api.authorize().subscribeOn(Schedulers.io())
}