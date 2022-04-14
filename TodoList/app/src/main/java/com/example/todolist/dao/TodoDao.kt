package com.example.todolist.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todolist.model.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    fun getAll(): List<Todo>
    @Query("SELECT * FROM todo WHERE id=:id")
    fun getById(id: Int): List<Todo>
    @Insert()
    fun insert(todo: Todo): Long
    @Update()
    fun update(todo: Todo): Unit
}