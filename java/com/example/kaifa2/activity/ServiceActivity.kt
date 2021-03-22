package com.example.kaifa2.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.example.kaifa2.R
import com.example.kaifa2.service.MyService
import kotlinx.android.synthetic.main.activity_service.*

class ServiceActivity : AppCompatActivity() {

    lateinit var downloadBinder: MyService.DownloadBinder

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            downloadBinder = service as MyService.DownloadBinder
            downloadBinder.startDownload()
            downloadBinder.getProgress()
        }

        override fun onServiceDisconnected(name: ComponentName) {
            Log.d("MyService", "onServiceDisconnected")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
        startServiceBtn.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            startService(intent) // 启动Service
        }
        stopServiceBtn.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            stopService(intent) // 停止Service
        }
        bindServiceBtn.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            bindService(intent, connection, Context.BIND_AUTO_CREATE) // 绑定Service
        }
        unbindServiceBtn.setOnClickListener {
            unbindService(connection) // 解绑Service
        }
    }

}

//
//import android.Manifest
//import android.app.NotificationChannel
//import android.app.NotificationManager
//import android.content.ComponentName
//import android.content.Context
//import android.content.Intent
//import android.content.ServiceConnection
//import android.content.pm.PackageManager
//import android.os.Build
//import android.os.Bundle
//import android.os.IBinder
//import android.view.View
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.appcompat.widget.Toolbar
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import com.example.kaifa2.R
//import com.example.kaifa2.service.MyService
//import kotlinx.android.synthetic.main.activity_service.*
//
//
//class ServiceActivity : AppCompatActivity() {
//    lateinit var manager: NotificationManager
//    lateinit var downloadBinder: MyService.DownloadBinder
//    private val connection=object :ServiceConnection{
//        override fun onServiceConnected(name: ComponentName, service: IBinder) {
//            downloadBinder=service as MyService.DownloadBinder
//            downloadBinder.startDownload()
//            //downloadBinder.getProgress()
//        }
//
//        override fun onServiceDisconnected(name: ComponentName) {
//
//        }
//    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_service)
//        manager=getSystemService(NOTIFICATION_SERVICE) as NotificationManager//消息的通知渠道
//        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){//通知渠道，判断是否大于8.0
//            val channel=NotificationChannel(
//                "Normal",
//                "Normal",
//                NotificationManager.IMPORTANCE_DEFAULT
//            )
//            manager.createNotificationChannel(channel)
//        }
//        if (ContextCompat.checkSelfPermission(
//                this,
//                Manifest.permission.READ_EXTERNAL_STORAGE
//            ) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
//                1
//            )
//        }
//        if (ContextCompat.checkSelfPermission(
//                this,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE
//            ) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
//                2
//            )
//        }
//
//        startDownloadbtn.setOnClickListener {
//            val intent=Intent(this, MyService::class.java)
//            bindService(intent, connection, BIND_AUTO_CREATE)
//        }
//        pauseDownloadbtn.setOnClickListener {
//            unbindService(connection)
//        }
//        cancleDownloadbtn.setOnClickListener {
//        }
//    }
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {//运行时权限的判断
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when(requestCode){
//            1 -> {
//                if (grantResults.isNotEmpty() && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(this, "拒绝写存储权限本程序将不能正常运行", Toast.LENGTH_SHORT).show()
//                }
//            }
//            2 -> {
//                if (grantResults.isNotEmpty() && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(this, "拒绝读存储权限本程序将不能正常运行", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }
//}
