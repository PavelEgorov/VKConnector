package com.egorovsoft.vkconnector.ui.fragment.attachments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf

import com.egorovsoft.vkconnector.R
import com.egorovsoft.vkconnector.mvp.model.attachments.ItemAttachments
import com.egorovsoft.vkconnector.mvp.presenter.fragment.AttachmentsPresenter
import com.egorovsoft.vkconnector.mvp.view.fragment.attachments.AttachmentsView
import kotlinx.android.synthetic.main.fragment_attachments.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class AttachmentsFragment() : MvpAppCompatFragment(), AttachmentsView {

    companion object {
        private val KEY = AttachmentsFragment::class.java.name + "extra.ATTACHMENT"
        fun newInstance(item: ItemAttachments) = AttachmentsFragment().apply {
            arguments = bundleOf(KEY to item)
        }
    }

    @InjectPresenter
    lateinit var presenter: AttachmentsPresenter

    @ProvidePresenter
    fun providePresenter() = AttachmentsPresenter().apply {
        val bundle = arguments
        bundle?.let {
            this.item = bundle.getParcelable(KEY)
        }
    }

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
