package com.example.kaifa2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kaifa2.R
import kotlinx.android.synthetic.main.activity_ok_http.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception
import kotlin.concurrent.thread

class OkHttp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ok_http)
        sendRequestBtn.setOnClickListener {
            sendRequestWithOkHttp()
        }
    }
    private fun sendRequestWithOkHttp(){
        thread {
            try {
                val client= OkHttpClient()
                val request= Request.Builder().url("https://www.baidu.com").build()
                val response=client.newCall(request).execute()
                val responseData=response.body?.string()
                if (responseData != null) {
                    showResponse(responseData)
                }
            }
            catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    private fun showResponse(response: String){
        runOnUiThread {
            responseTest.text=response
        }
    }
}