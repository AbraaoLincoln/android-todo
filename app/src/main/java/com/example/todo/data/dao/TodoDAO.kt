package com.example.todo.data.dao

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.todo.data.TodoDB
import com.example.todo.data.entity.Task
import com.example.todo.data.entity.Todo
import java.time.LocalDate
import kotlin.math.log

class TodoDAO(val dbW: SQLiteDatabase, val dbR: SQLiteDatabase): TodoInterface {

    override fun createTodo(name: String): Long {

        val values = ContentValues()
        values.put(TodoDB.TableTodo.NAME, name)
        values.put(TodoDB.TableTodo.CREATE_DATE, LocalDate.now().toString())
        val idNewTodo = dbW.insert(TodoDB.TableTodo.TABLE_NAME, null, values)

        return idNewTodo
    }

    override fun updateTodo(todo: Todo): Int {
        val values = ContentValues()
        values.put(TodoDB.TableTodo.NAME, todo.name)
        values.put(TodoDB.TableTodo.MODIFY_DATE, LocalDate.now().toString())

        val selection = "${TodoDB.TableTodo.ID} = ?"
        val selectionAgrs = arrayOf(todo.id.toString())

        val idTodoUpdate = dbW.update(TodoDB.TableTodo.TABLE_NAME, values, selection, selectionAgrs)

        return idTodoUpdate
    }

    override fun updateTodoModifyDate(todo: Todo): Int {
        val values = ContentValues()
        values.put(TodoDB.TableTodo.MODIFY_DATE, LocalDate.now().toString())

        val selection = "${TodoDB.TableTodo.ID} = ?"
        val selectionAgrs = arrayOf(todo.id.toString())

        val idTodoUpdate = dbW.update(TodoDB.TableTodo.TABLE_NAME, values, selection, selectionAgrs)

        return idTodoUpdate
    }

    override fun deleteTodo(todo: Todo): Int {
        val selection = "${TodoDB.TableTodo.ID} = ?"
        val selectionAgrs = arrayOf(todo.id.toString())
        Log.i("id to delete", todo.id.toString())
        val idTodoDeleted = dbW.delete(TodoDB.TableTodo.TABLE_NAME, selection, selectionAgrs)

        return idTodoDeleted
    }

    override fun loadTodos(): MutableList<Todo> {
        val orderBy = "${TodoDB.TableTodo.CREATE_DATE}"

        val cursor = dbR.query(TodoDB.TableTodo.TABLE_NAME,
                      null, null, null,
                      null, null, orderBy)

        val todos = mutableListOf<Todo>()

        with(cursor) {
            while(moveToNext()) {
                val todo = Todo(getInt(getColumnIndexOrThrow(TodoDB.TableTodo.ID)),
                                getString(getColumnIndexOrThrow(TodoDB.TableTodo.NAME)),
                                getString(getColumnIndexOrThrow(TodoDB.TableTodo.CREATE_DATE)),
                                getString(getColumnIndexOrThrow(TodoDB.TableTodo.MODIFY_DATE)))
                todos.add(todo)
            }
        }
        cursor.close()

        return todos
    }

    override fun loadTaks(todo: Todo): MutableList<Task> {
        TODO("Not yet implemented")
    }

}