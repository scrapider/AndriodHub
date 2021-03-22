package com.example.kaifa2.activity


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kaifa2.R
import com.example.kaifa2.activity.guimie.GuiVideo
import com.example.kaifa2.adapter.AnimeAdapterMaterial
import com.example.kaifa2.datatype.Anime
import kotlinx.android.synthetic.main.activity_login_success.*

class Login_success : BasicActivity() {
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
            R.id.down->{
                val intent = Intent(this, ServiceActivity::class.java)
                startActivity(intent)
            }
            //Toast.makeText(this,"you click add",Toast.LENGTH_SHORT).show()
            // 跳转页面 简介
        }
        return true
    }
    val anime = mutableListOf(
        Anime("刀剑神域", R.drawable.zongzi),
        Anime("鬼灭之刃", R.drawable.xiage),
        Anime("刀剑神域序列之争", R.drawable.daojianxulie),
        Anime("鬼灭之刃无限列车", R.drawable.guimiecar)
    )

    val animeList = ArrayList<Anime>()

    override fun onCreate(savedInstanceState: Bundle?) {
//        fruitImage.setOnClickListener {
//            val intent=Intent(this, Animation2::class.java)
//            startActivity(intent)
//        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_success)
        val extraDate = intent.getStringExtra("account_name")
        val text2: TextView = findViewById(R.id.text1)
        text2.setText("欢迎登陆"+extraDate)


//        val intent = Intent(this, Login_success::class.java)//跳转
//        intent.putExtra("account", extraDate)
//        startActivity(intent)
//        finish()
//        setSupportActionBar(toolbar)
//        supportActionBar?.let {
//            it.setDisplayHomeAsUpEnabled(true)
//            it.setHomeAsUpIndicator(R.drawable.诗乃)
//        }
//        navView.setCheckedItem(R.id.navCall)
//        navView.setNavigationItemSelectedListener {
//            drawerLayout.closeDrawers()
//            true
//        }
//        fab.setOnClickListener { view ->
//            view.showSnackbar("Data deleted", "Undo") {
//                "Data restored".showToast(this)
//            }
//        }

        initFruits()
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        val adapter = AnimeAdapterMaterial(this, animeList)
        recyclerView.adapter = adapter
        ButtonForceOffline.setOnClickListener {
            val intent=Intent("com.example.kaifa2.activity.FORCE_OFFLINE")
            sendBroadcast(intent)
        }
//    }
//        swipeRefresh.setColorSchemeResources(R.color.colorPrimary)
//        swipeRefresh.setOnRefreshListener {
//            refreshFruits(adapter)
//        }
    }

//    private fun refreshFruits(adapter: FruitAdapter) {
//        thread {
//            Thread.sleep(2000)
//            runOnUiThread {
//                initFruits()
//                adapter.notifyDataSetChanged()
//                swipeRefresh.isRefreshing = false
//            }
//        }
//    }
    private fun initFruits() {
        animeList.clear()
        for (i in anime) {
            animeList.add(i)
        }
//        repeat(4) {
//            val index = (0 until fruits.size).random()
//            fruitList.add(fruits[index])
//        }
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.toolbar, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
//            R.id.backup -> Toast.makeText(this, "You clicked Backup", Toast.LENGTH_SHORT).show()
//            R.id.delete -> Toast.makeText(this, "You clicked Delete", Toast.LENGTH_SHORT).show()
//            R.id.settings -> Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).show()
//        }
//        return true
//    }

}


//
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import android.view.Menu
//import android.view.MenuItem
//import android.widget.ImageView
//import android.widget.TextView
//import com.example.kaifa2.R
//import com.example.kaifa2.dataBase.DataBase
//import com.example.kaifa2.dataBase.MyDataBaseHelper
//import kotlinx.android.synthetic.main.activity_login_success.*
//
//class Login_success : BasicActivity() {
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
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login_success)
//        val extraDate = intent.getStringExtra("account_name")
//        Log.d("LoginSuccess","$extraDate")
//        //val data = intent.getStringArrayExtra("extra")
//        val text2:TextView = findViewById(R.id.text1)
//        text2.setText("欢迎登陆"+extraDate)
//        val ImageAnime:ImageView = findViewById(R.id.anime)
//        val ImageAnime2:ImageView = findViewById(R.id.anime2)
//        val dbHelper= MyDataBaseHelper(this, "BookStore",2)
//        ImageAnime.setOnClickListener{
////            val intent=Intent(this, Animation::class.java)
////            startActivity(intent)
//            dbHelper.writableDatabase
//            val intent=Intent(this, DataBase::class.java)
//            startActivity(intent)
//        }
//
//        ImageAnime2.setOnClickListener {
//            val intent=Intent(this, Animation2::class.java)
//            startActivity(intent)
//        }
//
//        ButtonForceOffline.setOnClickListener {
//            val intent=Intent("com.example.kaifa2.activity.FORCE_OFFLINE")
//            sendBroadcast(intent)
//        }
//    }
//}
//
