package com.egorovsoft.vkconnector.navigation

import androidx.fragment.app.Fragment
import com.egorovsoft.vkconnector.mvp.model.attachments.ItemAttachments
import com.egorovsoft.vkconnector.ui.fragment.attachments.AttachmentsFragment
import com.egorovsoft.vkconnector.ui.fragment.news.NewsFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class NewsScreen() : SupportAppScreen() {
        override fun getFragment(): Fragment = NewsFragment.newInstance()
    }
    class AttachmentsScreen(val item: ItemAttachments) : SupportAppScreen() {
        override fun getFragment(): Fragment = AttachmentsFragment.newInstance(item)
    }
}