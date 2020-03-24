package com.egorovsoft.vkconnector.mvp.model.news

import com.egorovsoft.vkconnector.mvp.model.attachments.ItemAttachments

class ItemNewsModel {
    private val news = mutableListOf(
        ItemNews("Title1",
            ItemAttachments("Title1_1", "body1")
        ),
        ItemNews("Title2",
            ItemAttachments("Title2_1", "body2")
        ),
        ItemNews("Title3",
            ItemAttachments("Title3_1", "body3")
        )
    )

    /// newsfeed.get() api vk
    fun getNews() = news
}