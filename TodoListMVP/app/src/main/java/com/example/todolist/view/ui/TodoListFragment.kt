package com.example.todolist.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.R
import com.example.todolist.contract.TodoListInterface
import com.example.todolist.model.Todo
import com.example.todolist.presenter.TodoListPresenter
import com.example.todolist.view.adapter.TodoListAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_todo_list.*


class TodoListFragment : Fragment(), TodoListInterface.ViewInterface,
    TodoListInterface.SelectListenInterface {
    private lateinit var presenter: TodoListPresenter
    private lateinit var todoList: ArrayList<Todo>
    private lateinit var adapter: TodoListAdapter
    private var neededItem = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_todo_list,
            container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = TodoListPresenter(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initView() {
        recycler_view.layoutManager = LinearLayoutManager(context)
        clear_list_button.setOnClickListener { presenter?.removeData() }
        remove_from_list_button.setOnClickListener { presenter?.removeDateFromList(neededItem) }
        view_details_button.setOnClickListener {
            var todo = todoList[neededItem]
            val bundle = bundleOf("todoId" to todo.id)
            findNavController().navigate(R.id.action_todoListFragment_to_todoDetailFragment, bundle)
        }
    }

    override fun getDataFromPresenter(value: ArrayList<Todo>) {
        todoList = value
        adapter = TodoListAdapter(todoList!!, this)
        recycler_view.adapter = adapter
    }

    override fun getArrayPosition(position: Int) {
        Toast.makeText(context, "" + todoList[position].id, Toast.LENGTH_SHORT).show()
        neededItem = position
    }
}