package com.sun.jetpack

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import kotlinx.coroutines.*

class MainActivity : ComponentActivity() {
    private val TAG = "CoroutineJob"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Note: Although job.cancel() is called
        // as it is too busy, it can't take care of cancel() function

        val job = GlobalScope.launch {
            Log.d(TAG, "Long running calculation is starting ...")

            // Note: Using withTimeout(), we can cancel any long-running task after any specific time.
            // we don't need any delay() function.

            withTimeout(2000L){
                for (i in 30..40) {
                    /*
                    Returns true when the current Job is still active (has not completed and was not cancelled yet).
                    Check this property in long-running computation loops to support cancellation:

                    while (isActive) {
                        // do some computation
                    }

                    This property is a shortcut for coroutineContext.isActive in the scope when CoroutineScope is available. See coroutineContext, isActive and Job.isActive.
                     */
                    if (isActive) {
                        Log.d(TAG, "fibonacci for $i :${fibonacci(i)}")
                    }
                }
            }
            Log.d(TAG, "Long running calculation ended.")
        }

        /*
        runBlocking {
            delay(4000L)
            job.cancel()
            Log.d(TAG, "Job Canceled.")
        }

         */
        setContent {

        }
    }

    fun fibonacci(n: Int): Int {
        if (n == 0 || n == 1) return n
        return fibonacci(n - 1) + fibonacci(n - 2)
    }
}