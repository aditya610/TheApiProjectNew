package com.blume.theapiproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_image_view.*
import kotlinx.android.synthetic.main.activity_posts.*
import okhttp3.*
import java.io.IOException

class ImageView : AppCompatActivity() {

    val imageLists = ArrayList<ImagesClass>()
    lateinit var sID :String
    var id = 0
    lateinit var name :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_view)

        intent?.let {
            id = it.getIntExtra("ALBUMID", 0)
            sID= id.toString()
        }

        rvImages.setLayoutManager(GridLayoutManager(this, 2))
        rvImages.adapter = ImagesAdapter(imageLists)

        PostsNetworkCall()


    }

    fun PostsNetworkCall()
    {
        progressBarIma.visibility = View.VISIBLE
        progressBarIma.setProgress(0)
        progressBarIma.max = 2000

        val client = OkHttpClient()
        val request = Request.Builder()
                .url("https://jsonplaceholder.typicode.com/albums/"+sID+"/photos")
                .build()

        client.newCall(request).enqueue(object : Callback
        {
            override fun onFailure(call: Call?, e: IOException?) {

            }

            override fun onResponse(call: Call?, response: Response?) {
                val result = response!!.body()!!.string()
                Log.d("TAG","onResponse: " + result)
                val gson = Gson()
                val collectionType = object : TypeToken<ArrayList<ImagesClass>>() {}.type
                val postsResult= gson.fromJson<ArrayList<ImagesClass>>(result, collectionType)
                imageLists.clear()
                imageLists.addAll(postsResult)

                this@ImageView.runOnUiThread{
                    rvImages.adapter.notifyDataSetChanged()
                    progressBarIma.visibility = View.GONE
                }

            }
        })
    }
}
