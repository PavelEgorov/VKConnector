package com.egorovsoft.vkconnector.ui.webview

import android.graphics.Bitmap
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.egorovsoft.vkconnector.mvp.presenter.SplashPresenter

class VKWebViewClient(val presenter: SplashPresenter): WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        request?.let {
            presenter.loadUrl(it.url.toString())
        }

        return true
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        url?.let {
            presenter.onAuthorizated(it.toString())
        }
    }
}