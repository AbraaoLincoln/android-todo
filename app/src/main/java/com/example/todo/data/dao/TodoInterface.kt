package com.example.todo.data.dao

import com.example.todo.data.entity.Todo
import com.example.todo.data.entity.Task

interface TodoInterface {
    //return the id of the new todo created
    fun createTodo(name: String) : Long

    //return the id of the updated todo
    fun updateTodo(todo: Todo) : Int

    //return the id of the updated todo
    fun updateTodoModifyDate(todo: Todo) : Int

    //return the id of the deleted todo
    fun deleteTodo(todo: Todo) : Int

    //return all the todo
    fun loadTodos() : MutableList<Todo>

    //return the list of task associate with the todo
    fun loadTaks(todo: Todo) : MutableList<Task>
}