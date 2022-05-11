package com.example.todolist.presenter

import com.example.todolist.contract.TodoDetailInterface
import com.example.todolist.model.Todo
import com.example.todolist.model.TodoDetailModel
import com.example.todolist.model.TodoListModel

class TodoDetailPresenter(_view: TodoDetailInterface.ViewInterface, todoId: Int) : TodoDetailInterface.PresenterInterface {
    private var view: TodoDetailInterface.ViewInterface = _view
    private var model: TodoDetailModel = TodoDetailModel(todoId)
    private var todo: Todo? = null

    init {
        view.initView()
        model.initData(this)
    }

    override fun toggleStatus() {
        model.toggleStatus()
    }

    override fun getUpdatedData(todo: Todo) {
        this.todo = todo
        view.getDataFromPresenter(todo)
    }
}