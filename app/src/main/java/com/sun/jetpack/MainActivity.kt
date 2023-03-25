package com.sun.jetpack

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import kotlinx.coroutines.*

class MainActivity : ComponentActivity() {

    private val TAG = "CoroutineContextTest"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
        }
        GlobalScope.launch {
            Log.d(TAG, "Coroutine inside GlobalScope (Thread): ${Thread.currentThread().name}")
            withContext(Dispatchers.Main) {
                Log.d(TAG, "Setting Text (Thread): ${Thread.currentThread().name}")
                // Implement seText to the UI
                // like textView.setText = demoNetworkCall1()
            }
        }
        
    }
    suspend fun demoNetworkCall1(): String {
        delay(4000L)
        return "answer from demoNetworkCall1"
    }

    suspend fun demoNetworkCall2(): String {
        delay(4000L)
        return "answer from demoNetworkCall2"
    }
}