package com.example.kaifa2.comment


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DataBaseHaplerComment(val context: Context, name: String, version: Int) :
    SQLiteOpenHelper(context, name, null, version) {
    private val createAnime =
        "create table comment(" + "id integer primary key autoincrement," + "account text," + "comment text)"



    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createAnime)
        Toast.makeText(context, "Create succeeded", Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldversion: Int, newversion: Int) {
        db.execSQL("drop table if exists comment")
        onCreate(db)
    }
}