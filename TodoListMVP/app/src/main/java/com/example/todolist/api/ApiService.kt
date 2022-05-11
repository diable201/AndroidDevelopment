package com.example.todolist.api

import com.example.todolist.model.Todo
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("todos/")
    fun getTodos(): Call<ArrayList<Todo>>

    @GET("todos/{todoId}/")
    fun getTodoById(@Path("todoId") todoId: Int): Call<Todo>
}