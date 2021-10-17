package com.example.todo.data.dao

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.todo.data.TodoDB
import com.example.todo.data.entity.Task

class TaskDAO(val dbW: SQLiteDatabase, val dbR: SQLiteDatabase): TaskInterface {
    override fun createTask(name: String, todoId: Int): Long {
        val values = ContentValues()
        values.put(TodoDB.TableTask.NAME, name)
        values.put(TodoDB.TableTask.DONE, 0)
        values.put(TodoDB.TableTask.ID_TODO, todoId)

        return dbW.insert(TodoDB.TableTask.TABLE_NAME, null, values)
    }

    override fun updateTask(task: Task): Int {
        TODO("Not yet implemented")
    }

    override fun updateTaskAsDone(taskId: Long): Long {
        TODO("Not yet implemented")
    }

    override fun updateTaskAsNotDone(taskId: Long): Long {
        TODO("Not yet implemented")
    }

    override fun updateTodoModifyDate(todoId: Int): Int {
        TODO("Not yet implemented")
    }

    override fun deleteTask(task: Task): Int {
        TODO("Not yet implemented")
    }

    override fun findAllTaksOfTodo(todoId: Int): MutableList<Task> {
        val selection = "${TodoDB.TableTask.ID_TODO} = ?"
        val selectionAgrs = arrayOf(todoId.toString())

        val cursor = dbR.query(TodoDB.TableTask.TABLE_NAME,
            null, selection, selectionAgrs,
            null, null, null)

        val tasks = mutableListOf<Task>()

        with(cursor) {
            while(moveToNext()) {
                val task = Task(getInt(getColumnIndexOrThrow(TodoDB.TableTask.ID)),
                                getString(getColumnIndexOrThrow(TodoDB.TableTask.NAME)),
                          getInt(getColumnIndexOrThrow(TodoDB.TableTask.DONE)) == 1,
                                getLong(getColumnIndexOrThrow(TodoDB.TableTask.ID_TODO)))
                tasks.add(task)
            }
        }
        cursor.close()

        return tasks
    }
}