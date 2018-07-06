package com.blume.theapiproject

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_list_layout.view.*

class ImagesAdapter(val imagesLists : ArrayList<ImagesClass>) : RecyclerView.Adapter<ImageViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val li = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = li.inflate(R.layout.image_list_layout,parent,false)
        return ImageViewHolder(itemView)
    }

    override fun getItemCount(): Int = imagesLists.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.tvPhotoTitle.text = imagesLists[position].title
        Picasso.get()
                .load(imagesLists[position].thumbnailUrl)
                .placeholder(R.drawable.ic_image_black_24dp)
                .error(R.drawable.ic_image_black_24dp)
                .into(holder.photo)
    }

}

class ImageViewHolder(itemView :View) : RecyclerView.ViewHolder(itemView)
{
    val tvPhotoTitle = itemView.tvPhotoTitle
    val photo = itemView.photo
}