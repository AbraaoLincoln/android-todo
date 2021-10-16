package com.example.todo.activity

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.adapter.ListTodoAdapter
import com.example.todo.data.TodoDBHelper
import com.example.todo.data.dao.TodoDAO
import com.example.todo.data.entity.Todo
import com.example.todo.dialog.AddTodoDialogFragment
import com.example.todo.dialog.DeleteTodoDialogFragment
import com.example.todo.dialog.UpdateTodoDialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: TodoDBHelper
    private lateinit var dialogAddTodo: AddTodoDialogFragment
    private lateinit var todoDao: TodoDAO
    private lateinit var toolbarMenu: Menu
    lateinit var todoSelected: Todo

    override public fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.mainToolbar))
        //Setting database
        dbHelper = TodoDBHelper(applicationContext)
        todoDao = TodoDAO(dbHelper.writableDatabase, dbHelper.readableDatabase)

        //Setting RecyclerView
        configRecyclerView(todoDao)

        //Setting AlertDialog
        dialogAddTodo = AddTodoDialogFragment(todoDao)
        configFabAddTodo()


        try {
            if(savedInstanceState != null)
                todoSelected = savedInstanceState?.getSerializable("todoSelected") as Todo

        } catch (e: ClassCastException) {
            e.printStackTrace()
            throw ClassCastException(("cast erro"))
        }
    }

    override fun onDestroy() {
        if(dbHelper != null) dbHelper.close()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.itens_overflowmenu_toolbar, menu)
        if (menu != null) {
            toolbarMenu = menu
            for(index in 0 until toolbarMenu.size()) {
                toolbarMenu.getItem(index).setVisible(false)
            }
        }
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btnEditTodo -> {
                UpdateTodoDialogFragment().show(supportFragmentManager,"updateTodo")
                true
            }
            R.id.btnDeleteTodo -> {
                DeleteTodoDialogFragment().show(supportFragmentManager,"deleteTodo")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun showMenuItens() {
        if (toolbarMenu != null) {
            //val showMenuItem = if(toolbarMenu.size() > 0) !toolbarMenu.getItem(0).isVisible else true
            for(index in 0 until toolbarMenu.size()) {
                toolbarMenu.getItem(index).setVisible(true)
            }
        }
    }

    fun configRecyclerView(todoDao: TodoDAO) {
        val rc:RecyclerView = findViewById(R.id.RecyclerView)

        //val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        val orientation = resources.configuration.orientation
        val spanCount = if (orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 3
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(applicationContext, spanCount)
        rc.layoutManager = layoutManager

        rc.adapter = ListTodoAdapter(this , todoDao.loadTodos())
    }

    fun configFabAddTodo() {
        val fabAddTodo: FloatingActionButton = findViewById(R.id.fabAddTodo)
        fabAddTodo.setOnClickListener{ view -> dialogAddTodo.show(supportFragmentManager,"addTodo") }
    }

    fun updateRecyclerView() {
        //configRecyclerView(todoDao)
        val rc:RecyclerView = findViewById(R.id.RecyclerView)
        rc.adapter = ListTodoAdapter(this , todoDao.loadTodos())
    }

    fun updateTodo(todoNameUpdated: String) {
        todoSelected.name = todoNameUpdated
        todoDao.updateTodo(todoSelected)
        updateRecyclerView()
    }

    fun deleteTodo() {
        todoDao.deleteTodo(todoSelected)
        updateRecyclerView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("todoSelected", todoSelected)

    }
}