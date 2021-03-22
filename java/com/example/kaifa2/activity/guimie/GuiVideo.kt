package com.example.kaifa2.activity.guimie

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kaifa2.R
import com.example.kaifa2.adapter.AnimeAdpter
import com.example.kaifa2.comment.Adapter
import com.example.kaifa2.comment.Comment
import com.example.kaifa2.comment.CommentActivity
import com.example.kaifa2.comment.DataBaseHaplerComment
import com.example.kaifa2.dataBase.MyDataBaseHelper
import com.example.kaifa2.datatype.Anime
import kotlinx.android.synthetic.main.activity_animation2.*
import kotlinx.android.synthetic.main.activity_comment.*
import kotlinx.android.synthetic.main.activity_gui_video.*
import kotlinx.android.synthetic.main.activity_sao_video.*
import kotlinx.android.synthetic.main.activity_sao_video.Pause
import kotlinx.android.synthetic.main.activity_sao_video.Play
import kotlinx.android.synthetic.main.activity_sao_video.RePlay
import kotlinx.android.synthetic.main.activity_sao_video.videoView

class GuiVideo : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.pinlun,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        when(item.itemId) {
            R.id.pin -> {
                val intent = Intent(this, CommentActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }
    private val animeList2=ArrayList<Comment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBaseHaplerComment(this, "Comments",2)
        setContentView(R.layout.activity_gui_video)
        val uri= Uri.parse("android.resource://$packageName/${R.raw.guimie}")
        videoView.setVideoURI(uri)
        Play.setOnClickListener {  //播放
            if (!videoView.isPlaying)
                videoView.start()
        }
        Pause.setOnClickListener {//暂停
            if(videoView.isPlaying)
                videoView.pause()
        }
        RePlay.setOnClickListener {//从新播放
            if(videoView.isPlaying)
                videoView.resume()
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        videoView.suspend()
    }
}