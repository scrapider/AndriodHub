package com.example.kaifa2.activity


import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kaifa2.R
import com.example.kaifa2.adapter.AnimeAdapterMaterial
import com.example.kaifa2.adapter.AnimeAdapterMaterialLastActivity
import com.example.kaifa2.adapter.AnimeAdpter2
import com.example.kaifa2.dataBase.MyDataBaseHelper
import com.example.kaifa2.datatype.Anime
import kotlinx.android.synthetic.main.activity_animation.*
import kotlinx.android.synthetic.main.activity_login_success.recyclerView
import kotlin.collections.ArrayList

class Animation : AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.login,menu)
        menuInflater.inflate(R.menu.phone,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        //跳转页面(隐式） 登录
        when(item.itemId) {
            R.id.call->{
                val intent=Intent(this,SystemCallPhone::class.java)
                startActivity(intent)
            }
            R.id.drop -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.phone->{
                val intent = Intent(this, PhoneCall::class.java)
                startActivity(intent)
            }
            //Toast.makeText(this,"you click add",Toast.LENGTH_SHORT).show()
            // 跳转页面 简介
        }
        return true
    }
        fun getDrawResourceID(resourceName: String?): Int {
        val res: Resources = resources
        return res.getIdentifier(resourceName, "drawable", packageName)
    }


    val animeList = ArrayList<Anime>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)

        initAnimes()
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        val adapter = AnimeAdpter2(this, animeList)
        recyclerView.adapter = adapter

        runHomePage.setOnClickListener {
            val intent=Intent(this,SaoHomePage::class.java)
            startActivity(intent)
        }

    }


    private fun initAnimes() {
        val dbHelper= MyDataBaseHelper(this, "Anime",2) //读取对应数据库
        val db=dbHelper.writableDatabase
        val cursor=db.rawQuery("select * from Anime",null)  //读取数据表内容
        if(cursor.moveToFirst()){  //移动光标输出每一行
            do{
                val name=cursor.getString(cursor.getColumnIndex("AnimeName"))
                animeList.add(Anime(name, getDrawResourceID(name)))  //添加数据

            }while(cursor.moveToNext())
        }


    }

}


//
//import android.content.Intent
//import android.content.res.Resources
//import android.os.Bundle
//import android.view.Menu
//import android.view.MenuItem
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.kaifa2.R
//import com.example.kaifa2.adapter.AnimeAdpter
//import com.example.kaifa2.dataBase.MyDataBaseHelper
//import com.example.kaifa2.datatype.Anime
//import kotlinx.android.synthetic.main.activity_animation.*
//
//class Animation : AppCompatActivity() {
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        //  return super.onCreateOptionsMenu(menu)
//        menuInflater.inflate(R.menu.login,menu)
//        menuInflater.inflate(R.menu.phone,menu)
//        return true
//    }
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        super.onOptionsItemSelected(item)
//        //跳转页面(隐式） 登录
//        when(item.itemId) {
//            R.id.drop -> {
//                val intent = Intent(this, LoginActivity::class.java)
//                startActivity(intent)
//            }
//            R.id.phone->{
//                val intent = Intent(this, PhoneCall::class.java)
//                startActivity(intent)
//            }
//            //Toast.makeText(this,"you click add",Toast.LENGTH_SHORT).show()
//            // 跳转页面 简介
//        }
//        return true
//    }
//    private val animeList=ArrayList<Anime>()
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_animation)
//        initAnime()
//        val layoutManager = LinearLayoutManager(this)
//        animerecyclerview.layoutManager=layoutManager
//        val adpter= AnimeAdpter(animeList)
//        animerecyclerview.adapter=adpter
//    }
//    fun getDrawResourceID(resourceName: String?): Int {
//        val res: Resources = resources
//        return res.getIdentifier(resourceName, "drawable", packageName)
//    }
//
//    private fun initAnime(){
//        val dbHelper= MyDataBaseHelper(this, "Anime",2)
//        val db=dbHelper.writableDatabase
//        val cursor=db.rawQuery("select * from Anime",null)
//        if(cursor.moveToFirst()){
//            do{
//                val name=cursor.getString(cursor.getColumnIndex("AnimeName"))
//                val pic=cursor.getString(cursor.getColumnIndex("picture"))
//                animeList.add(Anime(name, getDrawResourceID(name)))
//
//            }while(cursor.moveToNext())
//        }
////        val name=cursor.getString(cursor.getColumnIndex("AnimeName"))
////        animeList.add(
////            Anime(
////                name,
////                getDrawResourceID(name)
////            )
////        )
////        animeList.add(
////            Anime(
////                "桐人",
////                R.drawable.Kirito
////            )
////        )
////        animeList.add(
////            Anime(
////                "亚斯娜",
////                R.drawable.Asuna
////            )
////        )
//
//
//    }
//}