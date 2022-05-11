package com.example.todolist.presenter

import com.example.todolist.contract.TodoListInterface
import com.example.todolist.model.Todo
import com.example.todolist.model.TodoListModel

class TodoListPresenter(_view: TodoListInterface.ViewInterface) : TodoListInterface.PresenterInterface {
    private var view: TodoListInterface.ViewInterface = _view
    private var model: TodoListModel = TodoListModel()
    private var todoList: ArrayList<Todo>? = null

    init {
        view.initView()
        model.initData(this)
    }

    override fun addValue() {
        model.addValue()
    }

    override fun removeData() {
        model.removeData()
    }

    override fun removeDateFromList(position: Int) {
        model.removeDataFromList(position)
    }

    override fun getUpdatedData(value: ArrayList<Todo>) {
        todoList = value
        view.getDataFromPresenter(todoList!!)
    }
}