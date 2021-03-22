package com.example.kaifa2.activity.sao

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kaifa2.R
import kotlinx.android.synthetic.main.activity_sao_video.*

class SaoVideo1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sao_video1)
        val uri= Uri.parse("android.resource://$packageName/${R.raw.sao}")
        videoView.setVideoURI(uri)
        Play.setOnClickListener {
            if (!videoView.isPlaying)
                videoView.start()
        }
        Pause.setOnClickListener {
            if(videoView.isPlaying)
                videoView.pause()
        }
        RePlay.setOnClickListener {
            if(videoView.isPlaying)
                videoView.resume()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        videoView.suspend()
    }
}