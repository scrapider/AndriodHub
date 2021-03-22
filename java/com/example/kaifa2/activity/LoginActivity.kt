package com.example.kaifa2.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.kaifa2.R
import com.example.kaifa2.dataBase.LoginDataBase
import com.example.kaifa2.dataBase.LoginDataBaseHelper
import com.example.kaifa2.dataBase.MyDataBaseHelper
import com.example.kaifa2.datatype.Anime
import kotlinx.android.synthetic.main.login_activity.*

class LoginActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.login_activity)
        val prefs = getPreferences(Context.MODE_PRIVATE)
        val isRemember = prefs.getBoolean("remember_password", false)
        if (isRemember) {
            val accountprefs = prefs.getString("account", "")
            val passwordprefs = prefs.getString("password", "")
            inputaccount.setText(accountprefs)
            inputpassword.setText(passwordprefs)
            rememberpassword.isChecked = true
        }
        val button1: Button = findViewById(R.id.button1)

        jinxinzhuce.setOnClickListener {
            val intent= Intent(this, LoginDataBase::class.java)
            startActivity(intent)
        }

        button1.setOnClickListener {
            val account = inputaccount.text.toString()
            val password = inputpassword.text.toString()

            var i=true
            val dbHelper = LoginDataBaseHelper(this, "idaccount", 2)
            val db = dbHelper.writableDatabase
            val cursor = db.rawQuery("select * from idname", null)
            if(cursor!=null) {
                while (cursor.moveToNext()) {
                    val name = cursor.getString(cursor.getColumnIndex("account"))
                    val pic = cursor.getString(cursor.getColumnIndex("password"))
                    if (account == name && password == pic) {
                        i=false
                        val editor = prefs.edit()
                        if (rememberpassword.isChecked) {
                            editor.putBoolean("remember_password", true)
                            editor.putString("account", account)
                            editor.putString("password", password)
                        } else {
                            editor.clear()
                        }
                        editor.apply()
                        val intent = Intent(this, Login_success::class.java)//跳转
                        intent.putExtra("account_name", account)
                        startActivity(intent)
                        finish()
                        // Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            if(i) {
//                Toast.makeText(this, "用户名密码有误", Toast.LENGTH_SHORT).show()
                if (account.isEmpty()) {
                    Toast.makeText(this, "用户名为空", Toast.LENGTH_SHORT).show()
                } else {
                    AlertDialog.Builder(this).apply {
                        setTitle("警告")
                        setMessage("用户名密码错误，从新输入")
                        setCancelable(false)
                        setPositiveButton("确定") { Dialog, which -> }
                        setPositiveButton("取消") { Dialog, which -> }
                        show()
                    }
                }

            }
        }

//                if (cursor.moveToFirst()) {
//                do {
//                    val name = cursor.getString(cursor.getColumnIndex("account"))
//                    val pic = cursor.getString(cursor.getColumnIndex("password"))
//
//
//                    if (account == name && password == pic) {
//                        val editor = prefs.edit()
//                        if (rememberpassword.isChecked) {
//                            editor.putBoolean("remember_password", true)
//                            editor.putString("account", account)
//                            editor.putString("password", password)
//                        } else {
//                            editor.clear()
//                        }
//                        editor.apply()
//                        val intent = Intent(this, Login_success::class.java)//跳转
//                        intent.putExtra("account_name", account)
//                        startActivity(intent)
//                        finish()
//
//
//                        // Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show()
//                    } else {
////                Toast.makeText(this, "用户名密码有误", Toast.LENGTH_SHORT).show()
//                        if (account.isEmpty()) {
//                            Toast.makeText(this, "用户名为空", Toast.LENGTH_SHORT).show()
//                        } else {
//                            AlertDialog.Builder(this).apply {
//                                setTitle("警告")
//                                setMessage("用户名密码错误，从新输入")
//                                setCancelable(false)
//                                setPositiveButton("确定") { Dialog, which -> }
//                                setPositiveButton("取消") { Dialog, which -> }
//                                show()
//                            }
//                        }
//
//                    }
//                } while (cursor.moveToNext())
    }

//
//            if (account == "qzy" && password == "123") {
//                val editor = prefs.edit()
//                if (rememberpassword.isChecked) {
//                    editor.putBoolean("remember_password", true)
//                    editor.putString("account", account)
//                    editor.putString("password", password)
//                }else{
//                    editor.clear()
//                }
//                editor.apply()
//                val intent = Intent(this, Login_success::class.java)//跳转
//                intent.putExtra("account_name", account)
//
//                startActivity(intent)
//                finish()
//
//
//                // Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show()
//            } else {
////                Toast.makeText(this, "用户名密码有误", Toast.LENGTH_SHORT).show()
//                if (account.isEmpty()) {
//                    Toast.makeText(this, "用户名为空", Toast.LENGTH_SHORT).show()
//                } else {
//                    AlertDialog.Builder(this).apply {
//                        setTitle("警告")
//                        setMessage("用户名密码错误，从新输入")
//                        setCancelable(false)
//                        setPositiveButton("确定") { Dialog, which -> }
//                        setPositiveButton("取消") { Dialog, which -> }
//                        show()
//                    }
//                }


}

