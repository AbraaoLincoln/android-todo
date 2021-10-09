package com.example.todo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.data.entity.Todo

class ListTodoAdapter(val todos: MutableList<Todo>): RecyclerView.Adapter<ListTodoAdapter.CardViewTodo>() {

    class CardViewTodo(view: View): RecyclerView.ViewHolder(view) {
        var nomeLista: TextView? = null
        var criado: TextView? = null
        var modificado: TextView? = null

        init {
            nomeLista = view.findViewById(R.id.textViewNomeLista)
            criado = view.findViewById(R.id.textViewCriado)
            modificado = view.findViewById(R.id.textViewModificado)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewTodo {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_card_todo, parent, false)

        return CardViewTodo(view)
    }

    override fun onBindViewHolder(holder: CardViewTodo, position: Int) {
        val todo = todos.get(position)
        holder.nomeLista?.setText(todo.name)
        holder.criado?.setText(todo.create)
        holder.modificado?.setText(todo.modify)
    }

    override fun getItemCount() = todos.size
}