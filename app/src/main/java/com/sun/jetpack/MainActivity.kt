package com.sun.jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.*

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var sizeState by remember {
                mutableStateOf(100.dp)
            }

            val size by animateDpAsState(
                targetValue = sizeState,
                tween(
                    durationMillis = 2000
                )
                /* 3.keyframes{} animation
                keyframes {
                    durationMillis = 5000
                    sizeState at 0 with LinearEasing
                    sizeState * 1.5f at 2500 with LinearOutSlowInEasing
                    sizeState * 2f at 5000
                }

                 */

                /*2. spring() animation
                spring(
                    dampingRatio = Spring.DampingRatioHighBouncy
                )*/


                /*1. tween () animation
                tween(
                    durationMillis = 2000,
                    delayMillis = 200,
                    easing = LinearOutSlowInEasing
                )
                */
            )

            // Color animation : One color to another color with reverse ...

            val infiniteTransition = rememberInfiniteTransition()
            val customizeColor by infiniteTransition.animateColor(
                initialValue = Color.Cyan,
                targetValue = Color.Blue,
                animationSpec = infiniteRepeatable(
                    tween(
                        durationMillis = 1500
                    ),
                    repeatMode = RepeatMode.Reverse
                )
            )

            Box(
                modifier = Modifier
                    .background(customizeColor)
                    .size(size),
                contentAlignment = Alignment.Center
            ){
                Button(onClick = {
                    sizeState += 40.dp
                }) {
                    Text(text = "Make Larger")
                }
            }
        }
    }
}