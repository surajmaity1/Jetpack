package com.sun.jetpack

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    private val TAG = "Coroutine Job"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Coroutine job.join
        val job = GlobalScope.launch {
            repeat(10) {
                Log.d(TAG, "Coroutine is still working ...")
                delay(1000L)
            }
        }

        runBlocking {
            job.join()
            Log.d(TAG, "Main thread is continuing ...")
        }
        setContent {

        }
    }
}