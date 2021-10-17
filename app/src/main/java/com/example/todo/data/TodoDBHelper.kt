package com.example.todo.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.Serializable

class TodoDBHelper(context: Context): SQLiteOpenHelper(context, TodoDB.DB_NAME, null, TodoDB.DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL(TodoDB.createTableTodo)
            db.execSQL(TodoDB.createTableTask)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            db.execSQL(TodoDB.createTableTodo)
            db.execSQL(TodoDB.createTableTask)
        }
    }
}