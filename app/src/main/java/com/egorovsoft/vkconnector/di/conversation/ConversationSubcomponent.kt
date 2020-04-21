package com.egorovsoft.vkconnector.di.conversation

import com.egorovsoft.vkconnector.di.conversation.module.ConversationModule
import com.egorovsoft.vkconnector.mvp.presenter.fragment.ConversationPresenter
import com.egorovsoft.vkconnector.ui.fragment.conversation.ConversationFragment
import com.egorovsoft.vkconnector.ui.fragment.conversation.ConversationRvAdapter
import dagger.Subcomponent

@ConversationScope
@Subcomponent(
    modules = [
        ConversationModule::class
    ]
)
interface ConversationSubcomponent {
    fun inject(conversationFragment: ConversationFragment)
    fun inject(conversationPresenter: ConversationPresenter)

    fun inject(conversationRvAdapter: ConversationRvAdapter)
}