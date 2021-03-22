package com.example.kaifa2.comment

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.kaifa2.R
import com.example.kaifa2.activity.guimie.GuiVideo
import com.example.kaifa2.dataBase.MyDataBaseHelper
import kotlinx.android.synthetic.main.activity_comment.*
import kotlinx.android.synthetic.main.activity_data_base.*

class CommentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)
        val button1: Button = findViewById(R.id.button2)
        button1.setOnClickListener {
            val dbHelper= DataBaseHaplerComment(this, "Comments",2)
            val db=dbHelper.writableDatabase
            val accountprefs = "用户"
            val animeName = details.text.toString()
            db.execSQL("insert into comment(account, comment)values(?,?)", arrayOf(accountprefs,animeName))
        }

        button2.setOnClickListener {
            val intent=Intent(this,GuiVideo::class.java)
            startActivity(intent)
        }
    }

}