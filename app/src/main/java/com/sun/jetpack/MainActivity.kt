package com.sun.jetpack

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/"
class MainActivity : ComponentActivity() {
    private val TAG = "Coroutine"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyAPI::class.java)
        
        api.getComments().enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        for (comment in it) {
                            Log.d(TAG, comment.toString())
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Log.d(TAG, "Error: $t")
            }
        })
        setContent {

        }
    }
}