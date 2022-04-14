package com.example.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todolist.dao.TodoDao
import com.example.todolist.databinding.FragmentTodoDetailBinding
import com.example.todolist.model.Todo
import com.google.gson.Gson


class TodoDetailFragment(private val db: AppDatabase, private val todoDao: TodoDao) : Fragment() {
    private lateinit var binding: FragmentTodoDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodoDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val jsonTodo = arguments?.getString("todo", "").toString()
        val todo = Gson().fromJson(jsonTodo, Todo::class.java)
        displayTodo(todo)
        binding.todoStatus.setOnClickListener{
            todo.completed = todo.completed != true
            todoDao.update(todo)
            displayTodo(todo)
        }
    }

    private fun displayTodo(todo: Todo) {
        binding.todoTitle.text = todo.title
        binding.todoDescription.text = todo.description
        if (todo.completed!!) {
            binding.todoStatus.text = context?.getString(R.string.uncompleted)
            binding.todoStatus.background.setTint(activity?.resources!!.getColor(R.color.red))
        } else {
            binding.todoStatus.text = context?.getString(R.string.completed)
            binding.todoStatus.background.setTint(activity?.resources!!.getColor(R.color.green))
        }
        binding.todoDuration.text = todo.duration
    }
}