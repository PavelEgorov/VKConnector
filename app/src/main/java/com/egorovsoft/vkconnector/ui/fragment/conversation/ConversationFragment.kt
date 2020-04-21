package com.egorovsoft.vkconnector.ui.fragment.conversation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.egorovsoft.vkconnector.R
import com.egorovsoft.vkconnector.mvp.presenter.fragment.ConversationPresenter
import com.egorovsoft.vkconnector.mvp.view.fragment.conversation.ConversationView
import com.egorovsoft.vkconnector.mvp.view.fragment.conversation.IRvConversationPresenter
import com.egorovsoft.vkconnector.ui.MainApp
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_conversation.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class ConversationFragment : MvpAppCompatFragment(), ConversationView {
    companion object{
        fun newInstance() = ConversationFragment()
    }

    @InjectPresenter lateinit var presenter: ConversationPresenter

    val conversationComponent = MainApp.instance.conversationComponent
    lateinit var adapter: ConversationRvAdapter

    @ProvidePresenter
    fun providePresenter() = ConversationPresenter(
        AndroidSchedulers.mainThread()
    ).apply {
        conversationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_conversation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        conversationComponent.inject(this)
    }

    override fun init() {
        rv_conversation.layoutManager = LinearLayoutManager(context)
        adapter = ConversationRvAdapter(presenter.rvPresenter).apply {
            conversationComponent.inject(this)
        }
        rv_conversation.adapter = adapter;
    }

    override fun update() {
        adapter?.let {
            it.notifyDataSetChanged()
        }
    }

}
