package com.sun.jetpack

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import kotlinx.coroutines.*
import retrofit2.*
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

        GlobalScope.launch {

            // The way we prefer to use Coroutine with Retrofit
            // Another way to use Coroutine with Retrofit
            val response = api.getComments()

            if (response.isSuccessful) {
                for (item in response.body()!!) {
                    Log.d(TAG, item.toString())
                }
            }

            /*
            Note: How to get response object of retrofit request
            If an error occurred, now, we can handle it ...

            val response = api.getComments().awaitResponse()

            if (response.isSuccessful) {
                for (item in response.body()!!) {
                    Log.d(TAG, item.toString())
                }
            }
             */



            /*
            Here, we're getting list of comment directly
            Note: Here, if an error occurred, now, we can handle it ...

            val answer = api.getComments().await()
            for (ans in answer){
                Log.d(TAG, ans.toString())
            }
             */
        }
        setContent {

        }
    }
}