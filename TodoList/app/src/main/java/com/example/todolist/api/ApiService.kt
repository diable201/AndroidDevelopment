package com.example.todolist.api

import com.example.todolist.model.Todo
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("todos/")
    fun getTodos(): Call<List<Todo>>

    @GET("todos/{id}/")
    fun getTodoById(@Path("id") todoId: Int): Call<Todo>

    @GET("todos/")
    fun getTodosByUserId(
        @Query("completed") completed: Boolean,
        @Query("userId") userId: Int
    ): Call<List<Todo>>

    @FormUrlEncoded
    @POST("todos/")
    fun createTodo(
        @Field("userId") userId: Int,
        @Field("title") title: String,
        @Field("completed") completed: Boolean,
    ): Call<Todo>
}