package com.example.todolist.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todolist.R
import com.example.todolist.contract.TodoDetailInterface
import com.example.todolist.model.Todo
import com.example.todolist.presenter.TodoDetailPresenter
import com.example.todolist.presenter.TodoListPresenter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_todo_detail.*


class TodoDetailFragment : Fragment(), TodoDetailInterface.ViewInterface {
    private lateinit var presenter: TodoDetailPresenter
    private lateinit var todo: Todo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_todo_detail,
            container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val todoId = arguments?.getInt("todoId", -1)
        presenter = TodoDetailPresenter(this, todoId!!)
    }

    private fun displayTodo(todo: Todo) {
        todo_id.text = todo.id.toString()
        user_id.text = todo.userId.toString()
        todo_title.text = todo.title
        if (todo.completed!!) {
            todo_status.text = context?.getString(R.string.completed)
            todo_status.background.setTint(activity?.resources!!.getColor(R.color.green))
        } else {
            todo_status.text = context?.getString(R.string.uncompleted)
            todo_status.background.setTint(activity?.resources!!.getColor(R.color.red))
        }
    }

    override fun initView() {
        todo_status.setOnClickListener {
            presenter.toggleStatus()
            displayTodo(todo)
        }
    }

    override fun getDataFromPresenter(value: Todo) {
        todo = value
        displayTodo(todo)
    }
}