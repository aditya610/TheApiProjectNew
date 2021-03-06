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
            intent.putExtra("Name", "Main")
            startActivity(intent)
        }

        btnTodos.setOnClickListener{
            val intent = Intent(this, Todos::class.java)
            intent.putExtra("Name", "Main")
            startActivity(intent)
        }

        btnUsers.setOnClickListener{
            val intent = Intent(this, Users::class.java)
            startActivity(intent)
        }

        btnAlbums.setOnClickListener{
            val intent = Intent(this, Albums::class.java)
            startActivity(intent)
        }
    }


}
