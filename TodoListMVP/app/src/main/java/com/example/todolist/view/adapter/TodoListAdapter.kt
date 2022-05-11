package com.example.todolist.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.contract.TodoListInterface
import com.example.todolist.model.Todo

class TodoListAdapter(
    private var todoList: ArrayList<Todo>,
    private var selectListen1: TodoListInterface.SelectListenInterface
) :
    RecyclerView.Adapter<TodoListAdapter.MyViewHolder>() {
    private var selectListen: TodoListInterface.SelectListenInterface? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        initialization(holder, position)

        selectItem(holder, position)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    class MyViewHolder(todoView: View) : RecyclerView.ViewHolder(todoView) {
        var todoId: TextView = todoView.findViewById(R.id.todo_id)
        var todoTitle: TextView = todoView.findViewById(R.id.todo_title)
        var todoStatus: TextView = todoView.findViewById(R.id.todo_status)
    }

    private fun initialization(holder: MyViewHolder, position: Int) {
        todoList[position].apply {
            holder.todoId.text = this.id.toString()
            holder.todoTitle.text = this.title.toString()
            if (this.completed!!) {
                holder.todoStatus.text = holder.itemView.context.getString(R.string.completed)
            } else {
                holder.todoStatus.text = holder.itemView.context.getString(R.string.uncompleted)
            }
        }
    }

    private fun selectItem(holder: MyViewHolder, position: Int) {
        selectListen = selectListen1
        holder.itemView.setOnClickListener {
            selectListen?.getArrayPosition(position)
        }
    }
}

