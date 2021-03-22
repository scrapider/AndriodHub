package com.example.kaifa2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.kaifa2.R
import kotlinx.android.synthetic.main.activity_sao_home_page.*

class GuiMieHomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_mie_home_page)
        webView.settings.javaScriptEnabled=true
        webView.webViewClient= WebViewClient()
        webView.loadUrl("https://baike.baidu.com/item/%E9%AC%BC%E7%81%AD%E4%B9%8B%E5%88%83/19927905?fr=aladdin")
    }
}