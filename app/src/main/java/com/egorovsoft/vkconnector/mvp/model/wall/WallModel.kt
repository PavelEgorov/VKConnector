package com.egorovsoft.vkconnector.mvp.model.wall

import com.egorovsoft.vkconnector.mvp.model.IVKApi
import io.reactivex.rxjava3.schedulers.Schedulers

class WallModel(val api: IVKApi) {
    fun getWall(token: String, userId: Int) = api.wallGet(token, userId).subscribeOn(Schedulers.io())
    fun getById(token: String, id: String) = api.getById(token, id).subscribeOn(Schedulers.io())
}