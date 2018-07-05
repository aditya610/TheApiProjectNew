package com.blume.theapiproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPosts.setOnClickListener{
            val intent = Intent(this, Posts::class.java)
            startActivity(intent)
        }
    }


}
