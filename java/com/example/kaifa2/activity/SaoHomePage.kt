package com.example.kaifa2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.kaifa2.R
import kotlinx.android.synthetic.main.activity_sao_home_page.*

class SaoHomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sao_home_page)
        webView.settings.javaScriptEnabled=true
        webView.webViewClient= WebViewClient()
        webView.loadUrl("https://baike.baidu.com/item/%E5%88%80%E5%89%91%E7%A5%9E%E5%9F%9F/4026818?fr=aladdin")
    }
}