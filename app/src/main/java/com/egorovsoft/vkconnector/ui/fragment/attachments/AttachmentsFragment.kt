package com.egorovsoft.vkconnector.ui.fragment.attachments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.egorovsoft.vkconnector.R
import com.egorovsoft.vkconnector.mvp.model.attachments.ItemAttachments
import com.egorovsoft.vkconnector.mvp.presenter.fragment.AttachmentsPresenter
import com.egorovsoft.vkconnector.mvp.view.fragment.attachments.AttachmentsView
import kotlinx.android.synthetic.main.fragment_attachments.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class AttachmentsFragment(val item: ItemAttachments) : MvpAppCompatFragment(), AttachmentsView {

    companion object {
        fun newInstance(item: ItemAttachments) = AttachmentsFragment(item)
    }

    @InjectPresenter
    lateinit var presenter: AttachmentsPresenter

    @ProvidePresenter
    fun providePresenter() = AttachmentsPresenter(item)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_attachments, container, false)
    }

    override fun init() {

    }

    override fun setTitle(txt: String) {
        txttitle.text = txt
    }

    override fun setDescription(txt: String) {
        txtdescription.text = txt
    }
}
