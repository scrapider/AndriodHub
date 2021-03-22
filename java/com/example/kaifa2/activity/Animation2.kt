package com.example.kaifa2.activity


import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kaifa2.R
import com.example.kaifa2.adapter.AnimeAdpter2
import com.example.kaifa2.datatype.Anime
import kotlinx.android.synthetic.main.activity_animation.*
import kotlinx.android.synthetic.main.activity_login_success.recyclerView
import kotlin.collections.ArrayList

class Animation2 : AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.login, menu)
        menuInflater.inflate(R.menu.phone, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        //跳转页面(隐式） 登录
        when (item.itemId) {
            R.id.call->{
                val intent=Intent(this,SystemCallPhone::class.java)
                startActivity(intent)
            }
            R.id.drop -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.phone -> {
                val intent = Intent(this, PhoneCall::class.java)
                startActivity(intent)
            }
            //Toast.makeText(this,"you click add",Toast.LENGTH_SHORT).show()
            // 跳转页面 简介
        }
        return true
    }


    val animeList = ArrayList<Anime>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)

        initFruits()
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        val adapter = AnimeAdpter2(this, animeList)
        recyclerView.adapter = adapter

        runHomePage.setOnClickListener {
            val intent = Intent(this, GuiMieHomePage::class.java)
            startActivity(intent)
        }

    }

    val anime = mutableListOf(
        Anime("灶门祢豆子", R.drawable.guimie_neizike),
        Anime("我妻善逸", R.drawable.guimie_woqi),
        Anime("富冈义勇", R.drawable.guimie_fugang),
        Anime("嘴平伊之助", R.drawable.download)
    )

    private fun initFruits() {
        animeList.clear()
        for (i in anime) {
            animeList.add(i)
        }

    }

}

//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.kaifa2.datatype.Anime
//import com.example.kaifa2.adapter.AnimeAdpter
//import com.example.kaifa2.R
//import kotlinx.android.synthetic.main.activity_animation.*
//import kotlinx.android.synthetic.main.activity_animation2.*
//
//class Animation2 : AppCompatActivity() {
//    private val animeList=ArrayList<Anime>()
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_animation2)
//        initAnime()
//        val layoutManager = LinearLayoutManager(this)
//        animerecyclerview.layoutManager=layoutManager
//        val adpter= AnimeAdpter(animeList)
//        animerecyclerview.adapter=adpter
//    }
//    private fun initAnime(){
//        animeList.add(
//            Anime(
//                "诗乃",
//                R.drawable.sinon
//            )
//        )
//        animeList.add(
//            Anime(
//                "桐人",
//                R.drawable.kirito
//            )
//        )
//        animeList.add(
//            Anime(
//                "亚斯娜",
//                R.drawable.asuna
//            )
//        )
//    }
//}