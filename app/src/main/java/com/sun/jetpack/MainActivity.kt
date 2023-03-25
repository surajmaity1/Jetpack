package com.sun.jetpack

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val TAG = "CoroutineTest"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            Text(text = "Coroutine Learning")
        }
        GlobalScope.launch {
            delay(5000L)
            Log.d(TAG, "Coroutine wave Hello from ${Thread.currentThread().name}")
        }
        Log.d(TAG, "Coroutine wave Hello from ${Thread.currentThread().name}")
    }
}