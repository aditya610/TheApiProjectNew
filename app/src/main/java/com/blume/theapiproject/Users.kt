package com.blume.theapiproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_posts.*
import kotlinx.android.synthetic.main.activity_users.*
import okhttp3.*
import java.io.IOException

class Users : AppCompatActivity() {
    val usersLists = ArrayList<usersClass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        rvUsers.layoutManager = LinearLayoutManager(this)
        rvUsers.adapter = UserListAdapter(usersLists,this)

        UsersNetworkCall()


    }

    fun UsersNetworkCall()
    {
        progressBarUser.visibility = View.VISIBLE
        progressBarUser.setProgress(0)
        progressBarUser.max = 2000
        val client = OkHttpClient()
        val request = Request.Builder()
                .url("https://jsonplaceholder.typicode.com/users")
                .build()

        client.newCall(request).enqueue(object : Callback
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
                Log.d("Users","List :"+usersLists)

                this@Users.runOnUiThread{
                    rvUsers.adapter.notifyDataSetChanged()
                    progressBarUser.visibility = View.GONE
                }
            }
        })
    }
}
