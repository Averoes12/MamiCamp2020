package com.averoes.listmaker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.averoes.listmaker.R
import com.averoes.listmaker.utils.TaskList
import kotlinx.android.synthetic.main.todo_list_item.view.*

class TodoListAdapter(val taskList: ArrayList<TaskList>, val clickListener:TodoClickListener) : RecyclerView.Adapter<TodoListAdapter.Holder>() {

    interface TodoClickListener{

        fun itemClicked(list: TaskList)
    }

    fun addItem(list:TaskList) {
        taskList.add(list)
        notifyItemInserted(taskList.size -1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.todo_list_item, parent, false))

    }

    override fun getItemCount() = taskList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.numberItem.text = (position + 1).toString()
        holder.todoDescItem.text = taskList[position].name
        holder.itemView.setOnClickListener {
            clickListener.itemClicked(list = taskList[position])
        }
    }

    class Holder(itemView:View):RecyclerView.ViewHolder(itemView){

        val numberItem = itemView.number_item
        val todoDescItem = itemView.todo_desc_item

    }
}