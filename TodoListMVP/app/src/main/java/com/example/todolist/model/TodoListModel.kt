package com.example.todolist.model

import android.util.Log
import com.example.todolist.api.TodoClient
import com.example.todolist.contract.TodoListInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoListModel : TodoListInterface.ModelInterface {
    var todoList: ArrayList<Todo>? = null

    var presenter: TodoListInterface.PresenterInterface? = null

    override fun addValue() {
        // todoList.add()
    }

    override fun getValue() {
        TodoClient.getTodos().enqueue(object : Callback<ArrayList<Todo>> {
            override fun onResponse(
                call: Call<ArrayList<Todo>>,
                response: Response<ArrayList<Todo>>
            ) {
                Log.e(Todo::class.java.simpleName, response.toString())
                todoList = response.body()
                updateList()
            }

            override fun onFailure(call: Call<ArrayList<Todo>>, t: Throwable) {
                Log.e(Todo::class.java.simpleName, "error on get value", t)
            }
        })
    }

    override fun removeData() {
        todoList!!.clear()
        updateList()
    }

    override fun removeDataFromList(position: Int) {
        todoList!!.removeAt(position)
        updateList()
    }

    override fun updateList() {
        presenter!!.getUpdatedData(todoList!!)
    }

    fun initData(presenter: TodoListInterface.PresenterInterface) {
        this.presenter = presenter
        getValue()
    }
}