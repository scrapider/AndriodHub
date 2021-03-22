package com.example.kaifa2.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.kaifa2.R
import com.example.kaifa2.activity.MainActivity
import kotlin.concurrent.thread

class MyService : Service() {

    private val mBinder = DownloadBinder()

    class DownloadBinder : Binder() {

        fun startDownload() {
            Log.d("MyService", "startDownload executed")
        }

        fun getProgress(): Int {
            Log.d("MyService", "getProgress executed")
            return 0
        }

    }

    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("MyService", "onCreate executed")
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("my_service", "前台Service通知", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val intent = Intent(this, MainActivity::class.java)
        val pi = PendingIntent.getActivity(this, 0, intent, 0)
        val notification = NotificationCompat.Builder(this, "my_service")
            .setContentTitle("service标题")
            .setContentText("service内容")
            .setSmallIcon(R.drawable.ic_done)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.shaojiu))
            .setContentIntent(pi)
            .build()
        startForeground(1, notification)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d("MyService", "onStartCommand executed")
        thread {
            // 处理具体的逻辑
            stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyService", "onDestroy executed")
    }

}
//
//import android.annotation.SuppressLint
//import android.app.NotificationChannel
//import android.app.NotificationManager
//import android.app.Service
//import android.content.Context
//import android.content.Intent
//import android.graphics.BitmapFactory
//import android.os.*
//import android.widget.Toast
//import androidx.core.app.NotificationCompat
//import com.example.kaifa2.R
//import okhttp3.OkHttpClient
//import okhttp3.Request
//import java.io.File
//import java.io.InputStream
//import java.io.RandomAccessFile
//import kotlin.concurrent.thread
//
//class MyService : Service() {
//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        return super.onStartCommand(intent, flags, startId)
//    }
//
//    private val mBinder = DownloadBinder()
//
//    inner class DownloadBinder : Binder() {
//        //binder是嵌套类，必须要加inner才能使用外部类的getprocess方法
//        val TYPE_SUCCESS: Int = 0
//        val TYPE_FAILED: Int = 1
//        val TYPE_PAUSED: Int = 2
//        val TYPE_CANCLED: Int = 3
//        val isPaused: Boolean = false
//        val isCancled: Boolean = false
//
//        fun startDownload() {
//            //Log.d("MyService","startDownload:")
//            var a: Int = -1
//            val handler = @SuppressLint("HandlerLeak")
//            object : Handler() {
//                //处理子线程发过来的数据，thread中实现真正的下载任务
//                override fun handleMessage(msg: Message) {
//                    super.handleMessage(msg)
//                    a = msg.what
//                    when (a) {
//                        TYPE_SUCCESS -> {
//                            onSuccess()
//                        }
//                        TYPE_FAILED -> {
//                            onFailed()
//                        }
//                        TYPE_PAUSED -> {
//                            onPaused()
//                        }
//                        TYPE_CANCLED -> {
//                            onCancled()
//                        }
//                    }
//                }
//            }
//            thread {
//                val msg = Message()
//                var inputStream: InputStream? = null
//                var randomAccessFile: RandomAccessFile? = null
//                var file: File? = null
//                try {
//                    var downloadedLength: Long = 0
//                    var compareLength: Long = 0
//                    val downloadUrl: String =
//                        "https://partners-pamph.jnto.go.jp/simg/pamph/381.pdf"
//                    val filename: String = downloadUrl.substring(downloadUrl.lastIndexOf("/"))
//                    val directory: String =
//                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path
//                    file = File(directory + filename)
//                    if (file.exists()) {
//                        downloadedLength = file.length()
//                    }
//                    var contentLength: Long = getContentLength(downloadUrl)
//                    if (contentLength == compareLength) {
//                        msg.what = TYPE_FAILED
//                    } else if (contentLength == downloadedLength) {
//                        msg.what = TYPE_SUCCESS
//                    }
//                    val client = OkHttpClient()
//                    val request = Request.Builder().addHeader(
//                        "RANGE",
//                        "bytes=" + downloadedLength + "-"
//                    ).url(downloadUrl).build()
//                    val response = client.newCall(request).execute()
//                    if (response != null) {
//                        inputStream = response.body!!.byteStream()
//                        randomAccessFile = RandomAccessFile(file, "rw")
//                        randomAccessFile.seek(downloadedLength)
//                        val b: ByteArray = ByteArray(1024)
//                        var total: Int = 0
//                        val len: Int = inputStream.read(b)
//                        while ((len != -1) && ((total + downloadedLength.toInt()) <= (contentLength.toInt()))) {
//                            if (isCancled) {
//                                msg.what = TYPE_CANCLED
//                            } else if (isPaused) {
//                                msg.what = TYPE_PAUSED
//                            } else {
//                                total += len
//                                randomAccessFile.write(b, 0, len)
//                                val progress: Int =
//                                    (total + downloadedLength.toInt()) * 100 / (contentLength.toInt())
//                                getProgress(progress)
//                            }
//                        }
//                        response.body!!.close()
//                        msg.what = TYPE_SUCCESS
//                    }
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                } finally {
//                    try {
//                        if (inputStream != null) {
//                            inputStream.close()
//                        }
//                        if (randomAccessFile != null) {
//                            randomAccessFile.close()
//                        }
//                        if (isCancled && file != null) {
//                            file.delete()
//                        }
//                    } catch (e: Exception) {
//                        e.printStackTrace()
//                    }
//                }
//                handler?.sendMessage(msg)
//            }
//        }
//
//        private fun getContentLength(downloadUrl: String): Long {
//            try {
//                val client = OkHttpClient()
//                val request = Request.Builder().addHeader("Accept-Encoding", "identity")
//                    .url(downloadUrl)
//                    .build()
//                val response = client.newCall(request).execute()
//                if (response != null && response.isSuccessful) {
//                    val contentLength: Long = response.body!!.contentLength()
//                    response.body!!.close()
//                    return contentLength
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//            return 0
//        }
//    }
//
//    fun getProgress(progress: Int) {
//        //Log.d("MyService","getProgress:")
//        var manager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//通知渠道，判断是否大于8.0
//            val channel =
//                NotificationChannel("Normal", "Normal", NotificationManager.IMPORTANCE_DEFAULT)
//            manager.createNotificationChannel(channel)
//        }
//        val notification = NotificationCompat.Builder(this, "Normal")
//            .setContentTitle("Downloading....")
//            .setContentText("$progress%")
//            .setSmallIcon(R.drawable.shaojiu)
//            .setLargeIcon(
//                BitmapFactory.decodeResource(
//                    resources,
//                    R.drawable.wanzi
//                )
//            )
//            .setAutoCancel(true)
//            .build()
//        manager.notify(1, notification)
//    }
//
//    fun onSuccess() {
//        //Log.d("MyService","getProgress:")
//        var manager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//通知渠道，判断是否大于8.0
//            val channel =
//                NotificationChannel("Normal", "Normal", NotificationManager.IMPORTANCE_DEFAULT)
//            manager.createNotificationChannel(channel)
//        }
//        val notification = NotificationCompat.Builder(this, "Normal")
//            .setContentTitle("Downloaded Success")
//            // .setContentText("Downloaded Success")
//            .setSmallIcon(R.drawable.shaojiu)
//            .setLargeIcon(
//                BitmapFactory.decodeResource(
//                    resources,
//                    R.drawable.wanzi
//                )
//            )
//            .setAutoCancel(true)
//            .build()
//        manager.notify(2, notification)
//    }
//
//    fun onFailed() {
//        //Log.d("MyService","getProgress:")
//        var manager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//通知渠道，判断是否大于8.0
//            val channel =
//                NotificationChannel("Normal", "Normal", NotificationManager.IMPORTANCE_DEFAULT)
//            manager.createNotificationChannel(channel)
//        }
//        val notification = NotificationCompat.Builder(this, "Normal")
//            .setContentTitle("Downloaded Failed")
//            .setContentText("Downloaded Failed")
//            .setSmallIcon(R.drawable.shaojiu)
//            .setLargeIcon(
//                BitmapFactory.decodeResource(
//                    resources,
//                    R.drawable.wanzi
//                )
//            )
//            .setAutoCancel(true)
//            .build()
//        manager.notify(2, notification)
//    }
//
//    fun onPaused() {
//        //Log.d("MyService","getProgress:")
//        /*lateinit var manager:NotificationManager
//        manager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){//通知渠道，判断是否大于8.0
//            val channel= NotificationChannel("Normal","Normal", NotificationManager.IMPORTANCE_DEFAULT)
//            manager.createNotificationChannel(channel)
//        }
//        val notification= NotificationCompat.Builder(this,"Normal")
//            .setContentTitle("Downloaded Failed")
//            .setContentText("Downloaded Failed")
//            .setSmallIcon(R.drawable.shaojiu)
//            .setAutoCancel(true)
//            .build()
//        manager.notify(1,notification)*/
//        Toast.makeText(this, "Download Paused", Toast.LENGTH_SHORT).show()
//    }
//
//    fun onCancled() {
//        //Log.d("MyService","getProgress:")
//        /*lateinit var manager:NotificationManager
//        manager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){//通知渠道，判断是否大于8.0
//            val channel= NotificationChannel("Normal","Normal", NotificationManager.IMPORTANCE_DEFAULT)
//            manager.createNotificationChannel(channel)
//        }
//        val notification= NotificationCompat.Builder(this,"Normal")
//            .setContentTitle("Downloaded Failed")
//            .setContentText("Downloaded Failed")
//            .setSmallIcon(R.drawable.shaojiu)
//            .setAutoCancel(true)
//            .build()
//        manager.notify(1,notification)*/
//        Toast.makeText(this, "Download Cancled", Toast.LENGTH_SHORT).show()
//    }
//
//    override fun onBind(intent: Intent): IBinder {
//        return mBinder
//    }
//}
