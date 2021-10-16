package com.example.todo.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.todo.R
import com.example.todo.activity.MainActivity

class DeleteTodoDialogFragment(): DialogFragment() {
    private lateinit var mainActivity: MainActivity

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        if(activity != null) {
            val builder: AlertDialog.Builder = AlertDialog.Builder(activity)

            with(builder) {
                setTitle("Delete Todo")
                setMessage("Delete todo ${mainActivity.todoSelected.name} ?")
                setNegativeButton("no") {dialog, id -> {}}
                setPositiveButton("yes") { dialog, id ->
                    var text: String = "ops, error"

                    mainActivity.deleteTodo()
                    text = "${mainActivity.todoSelected.name} deleted with sucess"

                    Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
                }
            }

            return builder.create()
        }


        return super.onCreateDialog(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mainActivity = context as MainActivity

        } catch (e: ClassCastException) {
            throw ClassCastException((context.toString() +
                    " must be mainActivity"))
        }
    }
}