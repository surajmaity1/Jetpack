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

    private val TAG = "SuspendFunctionTest"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
            Text(text = "Suspend Function Learning")
        }

        GlobalScope.launch {
            val network1 = demoNetworkCall1()
            val network2 = demoNetworkCall2()

            Log.d(TAG, network1)
            Log.d(TAG, network2)
        }
    }

    suspend fun demoNetworkCall1(): String{
        delay(4000L)
        return "answer from demoNetworkCall1"
    }

    suspend fun demoNetworkCall2(): String{
        delay(4000L)
        return "answer from demoNetworkCall2"
    }
}