package com.example.kaifa2.dataBase

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kaifa2.R
import com.example.kaifa2.activity.Animation
import kotlinx.android.synthetic.main.activity_data_base.*


class DataBase : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_base)
        val button1: Button = findViewById(R.id.addAnimeData)

        fun getDrawResourceID(resourceName: String?): Int {
            val res: Resources = resources
            return res.getIdentifier(resourceName, "drawable", packageName)
        }

        val dbHelper=MyDataBaseHelper(this, "Anime",2)
        button1.setOnClickListener {
            val db=dbHelper.writableDatabase
            val animeName = inputAnimeName.text.toString()
            db.execSQL("insert into Anime(AnimeName, picture)values(?,?)", arrayOf(animeName,getDrawResourceID(animeName)))
            Toast.makeText(this,"添加数据成功",Toast.LENGTH_SHORT).show()
        }



        contentNextPage.setOnClickListener {
            val intent= Intent(this, Animation::class.java)
            startActivity(intent)
        }

        deleteAnimeData.setOnClickListener {
            val db=dbHelper.writableDatabase
            db.execSQL("delete from Anime")
        }
    }

}