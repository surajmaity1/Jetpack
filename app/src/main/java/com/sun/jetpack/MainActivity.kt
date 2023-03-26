package com.sun.jetpack

import android.content.Intent
import android.os.Bundle
import android.text.style.AlignmentSpan
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val TAG = "Coroutine"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(onClick = {

                    // Note, as here we use GlobalScope, it will be still executing if we go to another actvity
                    // There is memory leak
                    GlobalScope.launch {
                        while (true) {
                            delay(1000L)
                            Log.d(TAG, "Still running ...")
                        }
                    }

                    GlobalScope.launch {
                        delay(4000L)

                        val intent = Intent(this@MainActivity, AnotherActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                }) {
                    Text(text = "Go To Another Activity")
                }
            }

        }
    }
}