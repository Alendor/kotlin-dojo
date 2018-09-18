package com.compumovil.kotlin_dojo.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.compumovil.kotlin_dojo.Model.Joke
import com.compumovil.kotlin_dojo.R
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_chuck_joke.*
import okhttp3.*
import java.io.IOException

class ChuckJoke : AppCompatActivity() {

    val client = OkHttpClient()
    val serviceUrl = "https://api.chucknorris.io/jokes/random"
    val request = Request.Builder().url(serviceUrl).build()
    val gson = GsonBuilder().create()
    var joke = "Presiona el botón"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chuck_joke)
        jokeText.text = joke
        runService()
        changeJokeBtn.setOnClickListener {
            jokeText.text = joke
            runService()
        }
    }

    fun runService() {
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                val jokeObject = gson.fromJson(body, Joke::class.java)
                joke = jokeObject.value
            }

            override fun onFailure(call: Call?, e: IOException?) {
                joke = "Falló la petición"
                e?.printStackTrace()
            }
        })
    }
}
