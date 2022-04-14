package com.example.todolist

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolist.dao.TodoDao
import com.example.todolist.model.Category
import com.example.todolist.model.Todo

@Database(
    entities = [Todo::class, Category::class],
    version = 2,
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao
}