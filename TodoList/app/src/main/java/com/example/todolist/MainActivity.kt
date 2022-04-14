package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todolist.dao.TodoDao
import com.example.todolist.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: AppDatabase
    private lateinit var todoDao: TodoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        db = MainApplication.instance.getDatabase()!!
        todoDao = db.todoDao()

        val todoListFragment = TodoListFragment(db, todoDao)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, todoListFragment)
            commit()
        }
    }
}