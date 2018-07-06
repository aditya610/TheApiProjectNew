package com.blume.theapiproject


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.todos_list_items.view.*

class TodosAdapter(val todosLists :ArrayList<TodoClass>) : RecyclerView.Adapter<TodoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val li = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = li.inflate(R.layout.todos_list_items, parent, false)
        return TodoViewHolder(itemView)
    }

    override fun getItemCount(): Int = todosLists.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.tvTodo.text = todosLists[position].title
        holder.chkbox.isChecked = todosLists[position].completed
    }

}

class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    val tvTodo = itemView.tvTodo
    val chkbox = itemView.checkBox
}