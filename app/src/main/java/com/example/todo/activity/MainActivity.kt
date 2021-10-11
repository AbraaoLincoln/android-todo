package com.example.todo.activity

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.adapter.ListTodoAdapter
import com.example.todo.data.TodoDBHelper
import com.example.todo.data.dao.TodoDAO
import com.example.todo.data.entity.Todo
import com.example.todo.dialog.AddTodoDialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: TodoDBHelper
    private lateinit var dialogAddTodo: AddTodoDialogFragment
    private lateinit var todoDao: TodoDAO

    override public fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Setting database
        dbHelper = TodoDBHelper(applicationContext)
        todoDao = TodoDAO(dbHelper.writableDatabase, dbHelper.readableDatabase)

        //Setting RecyclerView
        configRecyclerView(todoDao)

        //Setting AlertDialog
        dialogAddTodo = AddTodoDialogFragment(todoDao)
        configFabAddTodo()

    }

    override fun onDestroy() {
        if(dbHelper != null) dbHelper.close()
        super.onDestroy()
    }

    fun configRecyclerView(todoDao: TodoDAO) {
        val rc:RecyclerView = findViewById(R.id.RecyclerView)

        //val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        val orientation = resources.configuration.orientation
        val spanCount = if (orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 3
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(applicationContext, spanCount)
        rc.layoutManager = layoutManager

        rc.adapter = ListTodoAdapter(todoDao.loadTodos())
    }

    fun configFabAddTodo() {
        val fabAddTodo: FloatingActionButton = findViewById(R.id.fabAddTodo)
        fabAddTodo.setOnClickListener({ view -> dialogAddTodo.show(supportFragmentManager,"addTodo") })
    }

    fun updateRecyclerView() {
        //configRecyclerView(todoDao)
        val rc:RecyclerView = findViewById(R.id.RecyclerView)
        rc.adapter = ListTodoAdapter(todoDao.loadTodos())
    }
}