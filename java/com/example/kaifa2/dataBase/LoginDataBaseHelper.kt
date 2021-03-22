package com.example.kaifa2.dataBase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class LoginDataBaseHelper(val context: Context, name: String, version: Int) :
    SQLiteOpenHelper(context, name, null, version) {
    private val createAnime =
        "create table idname(" + "id integer primary key autoincrement," + "account text," + "password text)"



    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createAnime)
//        db.execSQL(createCategory)
        Toast.makeText(context, "Create succeeded", Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldversion: Int, newversion: Int) {
        db.execSQL("drop table if exists idname")
//        db.execSQL("drop table if exists Category")
        onCreate(db)
    }
}
