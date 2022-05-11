package com.example.todolist.contract

import com.example.todolist.model.Todo

interface TodoDetailInterface {

    interface ViewInterface {
        fun initView()
        fun getDataFromPresenter(value: Todo)
    }

    interface PresenterInterface {
        fun toggleStatus()
        fun getUpdatedData(value: Todo)
    }

    interface ModelInterface {
        fun toggleStatus()
        fun getValue()
        fun updateValue()
    }
}