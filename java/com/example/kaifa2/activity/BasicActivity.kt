package com.example.kaifa2.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.kaifa2.ActivityCollector
import com.example.kaifa2.R

open class BasicActivity : AppCompatActivity() {
    lateinit var receiver: ForceOfflinelineReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_basic2)
        ActivityCollector().addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector().removeActivity(this)
    }

    override fun onResume() {
        super.onResume()
        val intentFilter=IntentFilter()
        intentFilter.addAction("com.example.kaifa2.activity.FORCE_OFFLINE")
        receiver=ForceOfflinelineReceiver()
        registerReceiver(receiver, intentFilter)

    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }


    inner class ForceOfflinelineReceiver:BroadcastReceiver(){
        override fun onReceive(p0: Context, p1: Intent) {
            AlertDialog.Builder(p0).apply {
                setTitle("警告")
                setMessage("您已经被强制下线了")
                setCancelable(false)
                setPositiveButton("确定"){_,_->ActivityCollector().finishAll()
                val i = Intent(context,LoginActivity::class.java)
                context.startActivity(i)
                }
                show()
            }
        }

    }
}