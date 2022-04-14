package com.example.todolist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Duration

@Entity
data class Category (
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "title") var title: String? = null,
)