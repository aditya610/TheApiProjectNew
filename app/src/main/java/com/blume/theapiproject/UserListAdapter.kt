package com.blume.theapiproject

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.users_list_layout.view.*

class UserListAdapter(val usersLists : ArrayList<usersClass>,
                      val context: Context)
                    : RecyclerView.Adapter<UserViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val li = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = li.inflate(R.layout.users_list_layout,parent,false)
        return UserViewHolder(itemView)
    }

    override fun getItemCount(): Int = usersLists.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        //Log.d("Users Adapter" ,"name : "+usersLists[position].name)
        holder.utvName.text = usersLists[position].name
        holder.utvUserName.text = usersLists[position].username
        holder.utvEmail.text = usersLists[position].email
        holder.utvPhone.text = usersLists[position].phone
        holder.utvWebsite.text = usersLists[position].website

        holder.ubtnTodos.setOnClickListener{
            val intent = Intent(context, Todos::class.java)
            intent.putExtra("USERID",usersLists[position].id)
            intent.putExtra("Name",usersLists[position].name)
            context.startActivity(intent)
        }

        holder.ubtnPosts.setOnClickListener{
            val intent = Intent(context, Posts::class.java)
            intent.putExtra("POSTID",usersLists[position].id)
            intent.putExtra("Name",usersLists[position].name)
            context.startActivity(intent)
        }
    }

}

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    val utvName = itemView.utvName!!
    val utvUserName = itemView.utvUsername!!
    val utvEmail = itemView.utvEmail!!
    val utvPhone = itemView.utvNumber!!
    val utvWebsite = itemView.utvWebsite!!
    val ubtnTodos = itemView.ubtnTodos!!
    val ubtnPosts = itemView.ubtnPosts!!
}