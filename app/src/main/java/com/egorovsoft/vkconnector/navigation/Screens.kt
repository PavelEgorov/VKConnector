package com.egorovsoft.vkconnector.navigation

import androidx.fragment.app.Fragment
import com.egorovsoft.vkconnector.mvp.model.attachments.ItemAttachments
import com.egorovsoft.vkconnector.ui.fragment.attachments.AttachmentsFragment
import com.egorovsoft.vkconnector.ui.fragment.conversation.ConversationFragment
import com.egorovsoft.vkconnector.ui.fragment.friends.FriendsFragment
import com.egorovsoft.vkconnector.ui.fragment.news.NewsFragment
import com.egorovsoft.vkconnector.ui.fragment.wall.WallFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class NewsScreen() : SupportAppScreen() {
        override fun getFragment(): Fragment = NewsFragment.newInstance()
    }

    class FriendsScreen() : SupportAppScreen() {
        override fun getFragment(): Fragment = FriendsFragment.newInstance()
    }

    class ConversationScreen() : SupportAppScreen() {
        override fun getFragment(): Fragment = ConversationFragment.newInstance()
    }

    class AttachmentsScreen(val item: ItemAttachments) : SupportAppScreen() {
        override fun getFragment(): Fragment = AttachmentsFragment.newInstance(item)
    }
    class WallScreen() : SupportAppScreen() {
        override fun getFragment(): Fragment = WallFragment.newInstance()
    }
}