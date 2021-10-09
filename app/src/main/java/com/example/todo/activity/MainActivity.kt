package com.example.todo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.adapter.ListTodoAdapter
import com.example.todo.data.TodoDBHelper
import com.example.todo.data.dao.TodoDAO
import com.example.todo.data.entity.Todo

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: TodoDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rc:RecyclerView = findViewById(R.id.RecyclerView)

        dbHelper = TodoDBHelper(applicationContext)
        val todoDao = TodoDAO(dbHelper.writableDatabase, dbHelper.readableDatabase)

        //todoDao.createTodo("lista1")
        //todoDao.createTodo("lista2")
        //todoDao.createTodo("lista3")

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        rc.layoutManager = layoutManager
        rc.adapter = ListTodoAdapter(todoDao.loadTodos())


    }

    override fun onDestroy() {
       // if(dbHelper != null) dbHelper.close()
        super.onDestroy()
    }
}