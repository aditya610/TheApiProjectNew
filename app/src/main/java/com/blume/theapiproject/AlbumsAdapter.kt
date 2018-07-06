package com.blume.theapiproject

import android.content.ClipData
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.simple_album_list.view.*
import android.content.ClipData.Item
import android.widget.Toast
import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import com.blume.theapiproject.R.id.parent


class AlbumsAdapter(val albumsLists : ArrayList<AlbumsClass>,
                    val usersLists : ArrayList<usersClass>,
                    val context: Context)
    : RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>(){

    val positionalUserNames = HashMap<Int, String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        if(positionalUserNames.isEmpty())
        {
            PositonUsers()
        }
        val li = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = li.inflate(R.layout.simple_album_list,parent,false)
        return AlbumsViewHolder(itemView)
    }

    override fun getItemCount(): Int = albumsLists.size

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        if(albumsLists[position].userId>10)
        {

        }
        else
        {
            holder.tvAlbumTitle.text = albumsLists[position].title
            val btnName  = findUserNameFromId(albumsLists[position].userId)
            holder.tvAuthor.text = btnName
            holder.id = albumsLists[position].id



        }

    }

    fun findUserNameFromId(id :Int) : String
    {
        Log.d("ADAPTER", "ID REQUESTED :" + id)
        return positionalUserNames[id]!!

    }



    fun PositonUsers()
    {
        val size = usersLists.size
        for(i in 0..(size-1))
        {
            positionalUserNames[usersLists[i].id] = usersLists[i].name
        }
        Log.d("ADAPTER", "Size of Hashmap :" + positionalUserNames.size)
        Log.d("ADAPTER", "Hashmap :" + positionalUserNames)
    }

    inner class AlbumsViewHolder(itemView :View) : RecyclerView.ViewHolder(itemView),View.OnClickListener{
        val tvAlbumTitle = itemView.tvAlbumTitle!!
        val tvAuthor = itemView.tvAuthor!!
        var id = 0

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            //Toast.makeText(context,"Fame :"+tvAlbumTitle.text,Toast.LENGTH_SHORT).show()
            val intent = Intent(context, ImageView::class.java)
            intent.putExtra("ALBUMID",id)
            context.startActivity(intent)
        }


    }
}





