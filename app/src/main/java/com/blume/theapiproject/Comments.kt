package com.blume.theapiproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_comments.*
import kotlinx.android.synthetic.main.activity_posts.*
import okhttp3.*
import java.io.IOException

class Comments : AppCompatActivity() {
    lateinit var post : PlaceHolderClass
    lateinit var sID :String
    lateinit var name :String
    val commentsLists = ArrayList<CommentsClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        intent?.let {
            val id = it.getIntExtra("POSTID", 0)
            name = it.getStringExtra("Name")
            sID= id.toString()
        }

        rvComments.layoutManager = LinearLayoutManager(this)
        rvComments.adapter = CommentsAdapter(commentsLists)
        PostsNetworkCall()

    }

        fun PostsNetworkCall()
        {
            progressBarCom.visibility = View.VISIBLE
            progressBarCom.setProgress(0)
            progressBarCom.max = 2000

            val client = OkHttpClient()
            val request = Request.Builder()
                    .url("https://jsonplaceholder.typicode.com/posts/"+sID)
                    .build()

            client.newCall(request).enqueue(object : Callback
            {
                override fun onFailure(call: Call?, e: IOException?) {

                }

                override fun onResponse(call: Call?, response: Response?) {
                    val result = response!!.body()!!.string()
                    Log.d("TAG","onResponse: " + result)
                    val gson = Gson()
                    //val collectionType = object : TypeToken<ArrayList<PlaceHolderClass>>() {}.type
                    val postsResult= gson.fromJson<PlaceHolderClass>(result, PlaceHolderClass::class.java)
                    post = postsResult
                    UsersNetworkCall()
                }
            })
        }


        fun UsersNetworkCall()
        {
            val client = OkHttpClient()
            val request = Request.Builder()
                    .url("https://jsonplaceholder.typicode.com/posts/"+sID+"/comments")
                    .build()

            client.newCall(request).enqueue(object :Callback
            {
                override fun onFailure(call: Call?, e: IOException?) {

                }

                override fun onResponse(call: Call?, response: Response?) {
                    val result = response!!.body()!!.string()
                    val collectionType = object : TypeToken<ArrayList<CommentsClass>>() {}.type
                    val gson = Gson()
                    val usersResult = gson.fromJson<ArrayList<CommentsClass>>(result, collectionType)
                    commentsLists.clear()
                    commentsLists.addAll(usersResult)

                    this@Comments.runOnUiThread{
                        CbtnUserName.text = name
                        CtvTitle.text = post.title
                        CtvBody.text = post.body
                        rvComments.adapter.notifyDataSetChanged()
                        progressBarCom.visibility = View.GONE
                    }
                }
            })
        }

    }
