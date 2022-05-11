package com.example.todolist.model

import android.util.Log
import com.example.todolist.api.TodoClient
import com.example.todolist.contract.TodoDetailInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoDetailModel(var todoId: Int) : TodoDetailInterface.ModelInterface {
    var todo: Todo? = null

    var presenter: TodoDetailInterface.PresenterInterface? = null

    override fun getValue() {
        TodoClient.getTodoById(todoId).enqueue(object : Callback<Todo> {
            override fun onResponse(
                call: Call<Todo>,
                response: Response<Todo>
            ) {
                Log.e(Todo::class.java.simpleName, response.toString())
                todo = response.body()
                updateValue()
            }

            override fun onFailure(call: Call<Todo>, t: Throwable) {
                Log.e(Todo::class.java.simpleName, "error on get value", t)
            }
        })
    }

    override fun toggleStatus() {
        todo!!.completed = !todo!!.completed!!
        updateValue()
    }

    override fun updateValue() {
        presenter!!.getUpdatedData(todo!!)
    }

    fun initData(presenter: TodoDetailInterface.PresenterInterface) {
        this.presenter = presenter
        getValue()
    }
}
