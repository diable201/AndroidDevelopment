package com.example.todolist.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Duration

@Entity(tableName = "todo")
data class Todo (
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "title") var title: String? = null,
    @ColumnInfo(name = "description") var description: String? = null,
    @ColumnInfo(name = "completed") var completed: Boolean? = null,
    @ColumnInfo(name = "duration") var duration: String? = null,
    @ColumnInfo(name = "used_id") var userId: Int? = null,
)