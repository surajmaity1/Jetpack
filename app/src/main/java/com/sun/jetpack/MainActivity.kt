package com.sun.jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

const val BASE_URL = "https://jsonplaceholder.typicode.com/"
class MainActivity : ComponentActivity() {
    private val TAG = "Coroutine"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            val job = launch {
                try {
                    delay(500L)
                }catch (e: Exception){
                    e.printStackTrace()
                }
                println("Coroutine 1 finished")
            }
            delay(300L)
            job.cancel()
        }

        /*
        val handler = CoroutineExceptionHandler { _, throwable ->
            println("Caught Exception: $throwable")
        }
        CoroutineScope(Dispatchers.Main + handler).launch {
            supervisorScope {
                launch {
                    delay(500L)
                    throw Exception("Coroutine 1 Finished")
                }
                launch {
                    delay(500L)
                    println("Coroutine 2 Failed")
                }
            }
        }

         */



        /*
        val handler = CoroutineExceptionHandler { _, throwable ->
            println("Caught Exception: $throwable")
        }

        lifecycleScope.launch(handler) {
            throw Exception("Error")
        }

         */

        /*
        val deferred = lifecycleScope.async {
            val string = async {
                delay(800L)
                throw Exception("Error")
                "Result"
            }
            //string.await()
        }
        lifecycleScope.launch {
//            deferred.await()
            try {
                deferred.await()
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }

         */


        /*
        lifecycleScope.async {
            val deferredTypeOfString = async {
                delay(800L)
                throw Exception("Error")
                "Result"
            }
            //string.await()
        }

         */


        /*
        Exception can't be handled using simply try catch block
        So, it'll give error
        lifecycleScope.launch {
            try {
                launch {
                    throw Exception()
                }
            } catch (e: Exception) {
                println("Exception handled.")
            }
        }

         */
    }
}