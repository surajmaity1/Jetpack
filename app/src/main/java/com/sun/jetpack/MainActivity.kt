package com.sun.jetpack

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    private val TAG = "Coroutine runBlocking{}"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Log.d(TAG, "Before runBlocking{}")
            runBlocking {
                // creating new Coroutine using launch{}
                launch {
                    Log.d(TAG, "Start of Coroutine 1")
                    delay(3000L)
                    Log.d(TAG, "End of Coroutine 1")
                }

                // creating new Coroutine using launch{}
                launch {
                    Log.d(TAG, "Start of Coroutine 2")
                    delay(3000L)
                    Log.d(TAG, "End of Coroutine 2")
                }

                // creating new Coroutine using launch{}
                launch {
                    Log.d(TAG, "Start of Coroutine 3")
                    delay(3000L)
                    Log.d(TAG, "End of Coroutine 3")
                }

                Log.d(TAG, "Start of runBlocking{}")
                delay(3000L)
                Log.d(TAG, "End of runBlocking{}")
            }
            Log.d(TAG, "After runBlocking{}")

            /*
            Both are similar --

            1. This is based on Thread

            Log.d(TAG, "Before Thread.sleep()")
            Thread.sleep(3000L)
            Log.d(TAG, "After Thread.sleep()")


            2. This is based on Coroutine

            Log.d(TAG, "Before runBlocking{}")
            runBlocking {
                Log.d(TAG, "Start of runBlocking{}")
                delay(3000L)
                Log.d(TAG, "End of runBlocking{}")
            }
            Log.d(TAG, "After runBlocking{}")
             */
        }
    }
}