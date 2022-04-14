package com.example.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.model.Todo

class RecyclerAdapter(
    private val todoList: List<Todo>,
    private val clickHandler: (Todo) -> Unit
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        todoList[position].apply {
            holder.todoId.text = this.id.toString()
            holder.todoTitle.text = this.title.toString()
            if (this.completed!!) {
                holder.todoStatus.text = holder.itemView.context.getString(R.string.uncompleted)
            } else {
                holder.todoStatus.text = holder.itemView.context.getString(R.string.completed)
            }
            holder.markButton.setOnClickListener {
                clickHandler(this)
            }
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    inner class ViewHolder(todoView: View) : RecyclerView.ViewHolder(todoView) {
        var todoId: TextView = todoView.findViewById(R.id.todo_id)
        var todoTitle: TextView = todoView.findViewById(R.id.todo_title)
        var todoStatus: TextView = todoView.findViewById(R.id.todo_status)
        var markButton: Button = todoView.findViewById(R.id.mark)
    }
}