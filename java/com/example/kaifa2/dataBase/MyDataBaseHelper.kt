package com.example.kaifa2.dataBase


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDataBaseHelper(val context: Context, name: String, version: Int) :
    SQLiteOpenHelper(context, name, null, version) {
    private val createAnime =
        "create table Anime(" + "id integer primary key autoincrement," + "AnimeName text," + "picture integer)"
    //构建数据库执行语句


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createAnime)//进行构建
        Toast.makeText(context, "Create succeeded", Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldversion: Int, newversion: Int) {
        db.execSQL("drop table if exists Anime")//删除资源
        onCreate(db)
    }
}
