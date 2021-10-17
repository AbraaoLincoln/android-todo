package com.example.todo.data.dao

import com.example.todo.data.entity.Task

interface TaskInterface {
    //return the id of the new task created
    fun createTask(name: String, todoId: Int) : Long

    //return the id of the updated task
    fun updateTask(task: Task) : Int

    fun updateTaskAsDone(taskId: Long): Long

    fun updateTaskAsNotDone(taskId: Long): Long

    //return the id of the updated task
    fun updateTodoModifyDate(todoId: Int) : Int

    //return the id of the deleted task
    fun deleteTask(task: Task) : Int

    //return the list of task associate with the todo
    fun findAllTaksOfTodo(todoId: Int) : MutableList<Task>
}