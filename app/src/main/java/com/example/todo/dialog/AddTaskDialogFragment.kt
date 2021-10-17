package com.example.todo.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.todo.R
import com.example.todo.activity.TaskActivity
import com.example.todo.data.dao.TaskDAO

class AddTaskDialogFragment(val taskDao: TaskDAO): DialogFragment() {
    private lateinit var taskActivity: TaskActivity

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        if(activity != null) {
            val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
            val inflater = requireActivity().layoutInflater
            with(builder) {
                setTitle("Create new task")
                setView(inflater.inflate(R.layout.fragment_input_add_todo, null))
                setNegativeButton("cancel") {dialog, id -> {}}
                setPositiveButton("create") { dialog, id ->
                    var text: String = "ops, error"

                    if(getDialog() != null) {
                        val textViewTodoName: TextView? = getDialog()?.findViewById(R.id.textViewTodoName)

                        if(textViewTodoName != null) {
                            val taskName = textViewTodoName.text.toString()

                            if(taskName != "") {
                                text = "${taskName} create with sucess"
                                taskDao.createTask(taskName, taskActivity.todo.id)
                                taskActivity.updateRecyclerView()
                            }
                        }
                    }

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
            taskActivity = context as TaskActivity

        } catch (e: ClassCastException) {
            throw ClassCastException((context.toString() +
                    " must be taskActivity"))
        }
    }
}