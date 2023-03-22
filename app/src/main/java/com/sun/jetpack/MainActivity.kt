package com.sun.jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            val scaffoldState = rememberScaffoldState()
            
            var textFieldState by remember{
                mutableStateOf("")
            }

            val scope = rememberCoroutineScope()

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                scaffoldState = scaffoldState
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 25.dp)
                ) {
                    TextField(
                        value = textFieldState,
                        onValueChange = { textFieldState = it },
                        label = { Text(text = "Enter Name")},
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Button(onClick = {
                        /*
                        Note: Never launch a coroutine directly in the composable,
                         it's only okay in callbacks such as an onClickListener
                         */
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Hello $textFieldState, Have a nice day!")
                        }
                    }) {
                        Text(text = "Show SnackBar")
                    }
                }

            }
        }
    }
}
