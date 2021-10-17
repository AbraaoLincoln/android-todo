package com.example.todo.activity

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.adapter.ListTaskAdapter
import com.example.todo.adapter.ListTodoAdapter
import com.example.todo.data.TodoDBHelper
import com.example.todo.data.dao.TaskDAO
import com.example.todo.data.dao.TodoDAO
import com.example.todo.data.entity.Todo
import com.example.todo.dialog.AddTaskDialogFragment
import com.example.todo.dialog.AddTodoDialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TaskActivity : AppCompatActivity() {
    lateinit var todo: Todo
    private lateinit var dbHelper: TodoDBHelper
    private lateinit var taskDao: TaskDAO
    private lateinit var dialogAddTask: AddTaskDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)
        setSupportActionBar(findViewById(R.id.taskToolbar))

        todo = intent.getSerializableExtra("todoName") as Todo

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = todo.name
        }

        dbHelper = TodoDBHelper(applicationContext)
        taskDao = TaskDAO(dbHelper.writableDatabase, dbHelper.readableDatabase)

        configRecyclerView(taskDao)

        dialogAddTask = AddTaskDialogFragment(taskDao)
        configFabAddTodo()

        try {
            if(savedInstanceState != null)
                todo = savedInstanceState?.getSerializable("todo") as Todo

        } catch (e: ClassCastException) {
            e.printStackTrace()
            throw ClassCastException(("cast erro"))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("todo", todo)
    }

    override fun onDestroy() {
        if(dbHelper != null) dbHelper.close()
        super.onDestroy()
    }

    fun configRecyclerView(taskDao: TaskDAO) {
        val rc: RecyclerView = findViewById(R.id.RecyclerView)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        rc.layoutManager = layoutManager

        rc.adapter = ListTaskAdapter(this , taskDao.findAllTaksOfTodo(todo.id))
    }

    fun updateRecyclerView() {
        val rc:RecyclerView = findViewById(R.id.RecyclerView)
        rc.adapter = ListTaskAdapter(this , taskDao.findAllTaksOfTodo(todo.id))
    }

    fun configFabAddTodo() {
        val fabAddTodo: FloatingActionButton = findViewById(R.id.fabAddTask)
        fabAddTodo.setOnClickListener{ view -> dialogAddTask.show(supportFragmentManager,"addTask") }
    }
}