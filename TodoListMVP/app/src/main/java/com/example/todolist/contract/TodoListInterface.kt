package com.example.todolist.contract

import com.example.todolist.model.Todo

interface TodoListInterface {

    interface ViewInterface {
        fun initView()
        fun getDataFromPresenter(value: ArrayList<Todo>)
    }

    interface PresenterInterface {
        fun addValue()
        fun removeData()
        fun removeDateFromList(position: Int)
        fun getUpdatedData(value: ArrayList<Todo>)
    }

    interface ModelInterface {
        fun addValue()
        fun getValue()
        fun removeData()
        fun removeDataFromList(position: Int)
        fun updateList()
    }

    interface SelectListenInterface {
        fun getArrayPosition(position: Int)
    }
}