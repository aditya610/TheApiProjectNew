package com.blume.theapiproject

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item_posts.view.*

class PostsRecyclerView(val postsLists : ArrayList<PlaceHolderClass>,
                        val usersLists : ArrayList<usersClass>) : RecyclerView.Adapter<PostsViewHolder>(){

    val positionalUserNames = HashMap<Int, String>()


    override fun getItemCount(): Int = postsLists.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        PositonUsers()
        val li = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = li.inflate(R.layout.list_item_posts, parent, false)
        return PostsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val btnName  = findUserNameFromId(postsLists[position].id)
        holder.btnUserName.setText(btnName)
        holder.tvBody.text = postsLists[position].body
        holder.tvTitle.text = postsLists[position].title

    }

    fun findUserNameFromId(id :Int) : String
    {
        return positionalUserNames[id]!!
    }

    fun PositonUsers()
    {
        val size = usersLists.size
        for(i in 0..(size-1))
        {
            positionalUserNames[usersLists[i].id] = usersLists[i].name
        }
    }


}

class PostsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    val btnUserName = itemView.btnUserName!!
    val tvTitle = itemView.tvTitle!!
    val tvBody = itemView.tvBody!!

}