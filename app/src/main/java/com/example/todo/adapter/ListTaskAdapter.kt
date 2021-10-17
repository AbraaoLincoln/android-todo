package com.example.todo.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.data.entity.Task

class ListTaskAdapter(val context: Context, val tasks: MutableList<Task>): RecyclerView.Adapter<ListTaskAdapter.TaskHolder>() {

    inner class TaskHolder(view: View): RecyclerView.ViewHolder(view) {
        val textViewTaskName: TextView = view.findViewById(R.id.textViewTaskName)
        lateinit var task: Task

        init {
            textViewTaskName.setOnClickListener { view ->
                Toast.makeText(context, "${task.name} selected", Toast.LENGTH_SHORT).show()
            }

            textViewTaskName.setOnLongClickListener{ view ->

                try {
                    //mainActivity.todoSelected = this.todoSelected
                    Toast.makeText(context, "${task.name} selected", Toast.LENGTH_SHORT).show()
                    //mainActivity.showMenuItens()

                } catch (e: ClassCastException) {
                    e.printStackTrace()
                    Toast.makeText(context, "ops", Toast.LENGTH_SHORT).show()
                }

                return@setOnLongClickListener true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_task_item, parent,false)
        val taskHolder = TaskHolder(view)

        return taskHolder
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        val task = tasks.get(position)
        holder.textViewTaskName.text = task.name
        holder.task = task

        Log.i("Task-name", task.name)
    }

    override fun getItemCount() = tasks.size
}