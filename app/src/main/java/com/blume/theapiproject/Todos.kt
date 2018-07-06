package com.blume.theapiproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_posts.*
import kotlinx.android.synthetic.main.activity_todos.*
import okhttp3.*
import java.io.IOException

class Todos : AppCompatActivity() {
    val todosLists = ArrayList<TodoClass>()
    lateinit var sID :String
    lateinit var name :String
    var id :Int =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todos)

        intent?.let {
            id = it.getIntExtra("USERID", 0)
            name = it.getStringExtra("Name")
            sID= id.toString()
        }

        rvTodos.layoutManager = LinearLayoutManager(this)
        rvTodos.adapter = TodosAdapter(todosLists)
        PostsNetworkCall()
    }

    fun PostsNetworkCall()
    {
        val custUrl :String
        progressBarTodo.visibility = View.VISIBLE
        progressBarTodo.setProgress(0)
        progressBarTodo.max = 2000

        val client = OkHttpClient()
        if(id!=0)
        {
            custUrl = "https://jsonplaceholder.typicode.com/users/"+sID+"/todos"
        }
        else
        {
            custUrl= "https://jsonplaceholder.typicode.com/todos"
        }
        val request = Request.Builder()
                .url(custUrl)
                .build()

        client.newCall(request).enqueue(object : Callback
        {
            override fun onFailure(call: Call?, e: IOException?) {

            }

            override fun onResponse(call: Call?, response: Response?) {
                val result = response!!.body()!!.string()
                Log.d("TAG","onResponse: " + result)
                val gson = Gson()
                val collectionType = object : TypeToken<ArrayList<TodoClass>>() {}.type
                val postsResult= gson.fromJson<ArrayList<TodoClass>>(result, collectionType)
                todosLists.clear()
                todosLists.addAll(postsResult)
                this@Todos.runOnUiThread{
                    rvTodos.adapter.notifyDataSetChanged()
                    progressBarTodo.visibility = View.GONE
                }
            }
        })
    }


}
