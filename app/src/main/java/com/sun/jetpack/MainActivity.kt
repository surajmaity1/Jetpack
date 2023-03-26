package com.sun.jetpack

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class MainActivity : ComponentActivity() {
    private val TAG = "Coroutine Async and Await"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Note: Here first networkCall1 and then networkCall2 will execute
        // We can check total time using measureTimeMillis{}

        // To solve this, we'll launch each coroutine for each networkCall,
        // Note: It is not a good practice, for learning, we'll see how it works
        // we'll implement alternative approach later.
        GlobalScope.launch(Dispatchers.IO) {

            val totalTime = measureTimeMillis {
                var networkCall1: String? = null
                var networkCall2: String? = null

                val job1 = launch {
                    networkCall1 = demoNetworkCall1()
                }
                val job2 = launch {
                    networkCall2 = demoNetworkCall2()
                }

                job1.join()
                job2.join()

                Log.d(TAG, "Response from network1 $networkCall1")
                Log.d(TAG, "Response from network2 $networkCall2")
            }
            Log.d(TAG, "Time Taken: $totalTime ms.")
        }
    }

    suspend fun demoNetworkCall1(): String {
        delay(1000L)
        return "answer from demoNetworkCall1"
    }

    suspend fun demoNetworkCall2(): String {
        delay(1000L)
        return "answer from demoNetworkCall2"
    }
}