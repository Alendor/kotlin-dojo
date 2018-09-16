package com.compumovil.kotlin_dojo.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.compumovil.kotlin_dojo.R
import kotlinx.android.synthetic.main.activity_chuck_joke.*
import okhttp3.*
import java.io.IOException

class ChuckJoke : AppCompatActivity() {

    val client = OkHttpClient()
    val serviceUrl = "https://api.chucknorris.io/jokes/random"
    val request = Request.Builder().url(serviceUrl).build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chuck_joke)

        runService()
        changeJokeBtn.setOnClickListener { runService() }
    }

    fun runService() {

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                println("response")
                println(body)
                Toast.makeText(this@ChuckJoke, body, Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call?, e: IOException?) {
                Toast.makeText(this@ChuckJoke, "Falló la petición", Toast.LENGTH_LONG).show()
                e?.printStackTrace()
            }
        })
    }
}
