package com.example.webview


import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    lateinit var browser:WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        browser = findViewById(R.id.myWebView)
        val url = "https://github.com/"
        browser.loadUrl(url)
        browser.settings.domStorageEnabled = true
        browser.settings.javaScriptEnabled = true
        browser.settings.cacheMode
        browser.canGoBackOrForward(30)

        browser.webViewClient = object :WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                val url = request?.url.toString()
                view?.loadUrl(url)
                return true
            }

        }
        val forward:ImageView= findViewById(R.id.forward)
        if(browser.canGoForward()){
            forward.visibility = View.VISIBLE
        }
        else{
            forward.visibility = View.GONE
        }
        forward.setOnClickListener {
            browser.goForward()
        }
    }

    override fun onBackPressed() {
        if(browser.canGoBack()){
            browser.goBack()
        }
        else
        super.onBackPressed()
    }
}