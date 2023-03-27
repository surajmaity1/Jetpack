package com.sun.jetpack

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await

data class Person(
    val name: String = "",
    val age: Int = -1
)
class MainActivity : ComponentActivity() {
    private val TAG = "Coroutine"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tutorialDocument = Firebase.firestore
            .collection("coroutines")
            .document("tutorial")
        val peter = Person("Peter", 23)

        GlobalScope.launch(Dispatchers.IO) {
            delay(3000L)
            tutorialDocument.set(peter).await()

            val person = tutorialDocument.get()
                .await().toObject(Person::class.java)

            // Printing in logcat
            Log.d(TAG, "Message: ${person.toString()}")
        }
    }
}