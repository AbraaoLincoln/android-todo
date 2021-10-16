package com.example.todo.data.entity

import java.io.Serializable

data class Todo(val id: Int = 0, var name: String = "", var create: String? = null, var modify: String? = null) : Serializable{
}