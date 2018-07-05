package com.blume.theapiproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_posts.*
import okhttp3.*
import java.io.IOException
import java.lang.reflect.Type

class Posts : AppCompatActivity() {
    val postsLists = ArrayList<PlaceHolderClass>()
    val usersLists = ArrayList<usersClass>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)


        rvPosts.layoutManager = LinearLayoutManager(this)
        rvPosts.adapter = PostsRecyclerView(postsLists,usersLists)

        PostsNetworkCall()
    }

    fun PostsNetworkCall()
    {
        progressBar.visibility = View.VISIBLE
        progressBar.setProgress(0)
        progressBar.max = 2000

        val client = OkHttpClient()
        val request = Request.Builder()
                .url("https://jsonplaceholder.typicode.com/posts")
                .build()

        client.newCall(request).enqueue(object :Callback
        {
            override fun onFailure(call: Call?, e: IOException?) {

            }

            override fun onResponse(call: Call?, response: Response?) {
                val result = response!!.body()!!.string()
                Log.d("TAG","onResponse: " + result)
                val gson = Gson()
                val collectionType = object : TypeToken<ArrayList<PlaceHolderClass>>() {}.type
                val postsResult= gson.fromJson<ArrayList<PlaceHolderClass>>(result, collectionType)
                postsLists.clear()
                postsLists.addAll(postsResult)
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

                this@Posts.runOnUiThread{
                    rvPosts.adapter.notifyDataSetChanged()
                    progressBar.visibility = View.GONE
                }
            }
        })
    }
}
