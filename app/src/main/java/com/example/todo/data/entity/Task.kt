package com.example.todo.data.entity

data class Task(val id: Int, var name: String, var done: Boolean = false, val todoId: Long) {
}