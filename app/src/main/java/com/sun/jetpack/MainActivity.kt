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

        // To solve this, we'll launch each coroutine for each networkCall, we'll using async
        GlobalScope.launch(Dispatchers.IO) {

            val totalTime = measureTimeMillis {

                val getResponse1 = async {
                    demoNetworkCall1()
                }
                val getResponse2 = async {
                    demoNetworkCall2()
                }

                Log.d(TAG, "Response from network1: ${getResponse1.await()}")
                Log.d(TAG, "Response from network2: ${getResponse2.await()}")
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