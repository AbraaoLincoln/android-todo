package com.example.todo.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.activity.MainActivity
import com.example.todo.data.entity.Todo

class ListTodoAdapter(val context:Context, val todos: MutableList<Todo>): RecyclerView.Adapter<ListTodoAdapter.CardViewTodo>() {
    private val mainActivity = context as MainActivity

    inner class CardViewTodo(view: View): RecyclerView.ViewHolder(view) {
        var nomeLista: TextView = view.findViewById(R.id.textViewNomeLista)
        var criado: TextView = view.findViewById(R.id.textViewCriado)
        var modificado: TextView = view.findViewById(R.id.textViewModificado)
        var cardViewTodo: CardView = view.findViewById(R.id.cardViewTodo)
        lateinit var todoSelected: Todo

        init {
            cardViewTodo.setOnLongClickListener{ view ->

                try {
                    mainActivity.todoSelected = this.todoSelected
                    Toast.makeText(context, "${todoSelected.name} selected", Toast.LENGTH_SHORT).show()
                    mainActivity.showMenuItens()

                } catch (e: ClassCastException) {
                    e.printStackTrace()
                    Toast.makeText(context, "ops", Toast.LENGTH_SHORT).show()
                }

                return@setOnLongClickListener true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewTodo {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_card_todo, parent, false)
        val cvt = CardViewTodo(view)
        //cvt.context = this.context
        return cvt
    }

    override fun onBindViewHolder(holder: CardViewTodo, position: Int) {
        val todo = todos.get(position)
        holder.nomeLista?.setText(todo.name)
        holder.criado?.setText("criado em: ${todo.create}")
        holder.modificado?.setText("modificado: ${todo.modify ?: "null"}")
        holder.todoSelected = todo
    }

    override fun getItemCount() = todos.size
}