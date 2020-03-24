package com.egorovsoft.vkconnector.mvp.model.news

import com.egorovsoft.vkconnector.mvp.model.attachments.ItemAttachments

data class ItemNews(
    val text : String,
    val attachments: ItemAttachments
)