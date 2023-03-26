package com.sun.jetpack

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class MainActivity : ComponentActivity() {
    private val TAG = "Coroutine Async and Await"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Note: Here first networkCall1 and then networkCall2 will execute
        // We can check total time using measureTimeMillis{}
        GlobalScope.launch(Dispatchers.IO) {
            val totalTime = measureTimeMillis {
                val networkCall1 = demoNetworkCall1()
                val networkCall2 = demoNetworkCall2()

                Log.d(TAG, "Response from network1 ${demoNetworkCall1()}")
                Log.d(TAG, "Response from network2 ${demoNetworkCall2()}")
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