package com.compumovil.kotlin_dojo.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.compumovil.kotlin_dojo.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, ChuckJoke::class.java))
        }
    }
}
