package com.example.kaifa2.dataBase

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.kaifa2.R
import com.example.kaifa2.activity.Animation
import com.example.kaifa2.activity.LoginActivity
import kotlinx.android.synthetic.main.activity_data_base.*
import kotlinx.android.synthetic.main.activity_login_data_base.*

class LoginDataBase : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_data_base)
        val button1: Button = findViewById(R.id.addaccount)

        val dbHelper= LoginDataBaseHelper(this, "idaccount",2)
        button1.setOnClickListener {
            val db=dbHelper.writableDatabase
            val accountname = id.text.toString()
            val pass = password.text.toString()
            val passid = passwordid.text.toString()
            if (pass!=passid)
                Toast.makeText(this,"确认密码不一致",Toast.LENGTH_SHORT).show()
            else{
                db.execSQL("insert into idname(account, password)values(?,?)", arrayOf(accountname,pass))
                Toast.makeText(this,"注册成功跳转登陆页面",Toast.LENGTH_SHORT).show()
                val intent= Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }

        }

    }

}