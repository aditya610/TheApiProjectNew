package com.blume.theapiproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_albums.*
import kotlinx.android.synthetic.main.activity_posts.*
import okhttp3.*
import java.io.IOException

class Albums : AppCompatActivity() {

    val albumsLists = ArrayList<AlbumsClass>()
    val usersLists = ArrayList<usersClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)

        rvAlbums.layoutManager = LinearLayoutManager(this)
        rvAlbums.adapter = AlbumsAdapter(albumsLists,usersLists,this)

        AlbumsNetworkCall()

    }

    fun AlbumsNetworkCall()
    {
        progressBarAlbum.visibility = View.VISIBLE
        progressBarAlbum.setProgress(0)
        progressBarAlbum.max = 2000

        val client = OkHttpClient()
        val request = Request.Builder()
                .url("https://jsonplaceholder.typicode.com/albums")
                .build()

        client.newCall(request).enqueue(object : Callback
        {
            override fun onFailure(call: Call?, e: IOException?) {

            }

            override fun onResponse(call: Call?, response: Response?) {
                val result = response!!.body()!!.string()
                Log.d("TAG","onResponse: " + result)
                val gson = Gson()
                val collectionType = object : TypeToken<ArrayList<AlbumsClass>>() {}.type
                val postsResult= gson.fromJson<ArrayList<AlbumsClass>>(result, collectionType)
                albumsLists.clear()
                albumsLists.addAll(postsResult)
                UsersNetworkCall()
            }
        })
    }

    fun UsersNetworkCall()
    {
        val client = OkHttpClient()
        val request = Request.Builder()
                .url("https://jsonplaceholder.typicode.com/users")
                .build()

        client.newCall(request).enqueue(object :Callback
        {
            override fun onFailure(call: Call?, e: IOException?) {

            }

            override fun onResponse(call: Call?, response: Response?) {
                val result = response!!.body()!!.string()
                val collectionType = object : TypeToken<ArrayList<usersClass>>() {}.type
                val gson = Gson()
                val usersResult = gson.fromJson<ArrayList<usersClass>>(result, collectionType)
                usersLists.clear()
                usersLists.addAll(usersResult)

                this@Albums.runOnUiThread{
                    rvAlbums.adapter.notifyDataSetChanged()
                    progressBarAlbum.visibility = View.GONE
                }
            }
        })
    }



}
