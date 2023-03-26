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
    private val TAG = "CoroutineJob"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Note: Although job.cancel() is called
        // as it is too busy, it can't take care of cancel() function

        val job = GlobalScope.launch {
            Log.d(TAG, "Long running calculation is starting ...")

            for (i in 30..40)
                Log.d(TAG, "fibonacci for $i :${fibonacci(i)}")

            Log.d(TAG, "Long running calculation ended.")
        }

        runBlocking {
            delay(4000L)
            job.cancel()
            Log.d(TAG, "Main thread is continuing ...")
        }
        setContent {

        }
    }

    fun fibonacci(n: Int) : Int{
        if (n == 0 || n == 1) return n
        return fibonacci(n-1) + fibonacci(n-2)
    }
}