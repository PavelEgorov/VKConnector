package com.egorovsoft.vkconnector.mvp.model

object Const {
    val programID = 7383836
    val redirectUri = "https://oauth.vk.com/blank.html"
    val versionAPI = "5.122"

    val notSupportedList: MutableList<String> = mutableListOf("page", "album", "photos_list", "market", "market_album", "sticker", "pretty_cards", "event")
}