package com.blume.theapiproject

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_view_comments.view.*

class CommentsAdapter(val commentsList : ArrayList<CommentsClass>)
    : RecyclerView.Adapter<CommentsViewHolder>(){

    override fun getItemCount(): Int = commentsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        val li = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = li.inflate(R.layout.list_view_comments,parent,false)
        return CommentsViewHolder(itemView)
    }



    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {

        holder.tvEmail.text = commentsList[position].email
        holder.tvComment.text = commentsList[position].body

    }

}

class CommentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    val tvEmail = itemView.btnEmail
    val tvComment = itemView.tvComment
}