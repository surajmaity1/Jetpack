package com.sun.jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CustomCircularProgressBar(percentage = 0.8f, num = 100)
            }
        }
    }
}

@Composable
fun CustomCircularProgressBar (
    percentage: Float,
    num: Int,
    fontSize: TextUnit = 60.sp,
    radius: Dp = 80.dp,
    color: Color = Color.Red,
    strokeWidth: Dp = 10.dp,
    animeDuration: Int = 1000,
    animeDelay: Int = 0
) {
    var isAnimationPlayed by remember {
        mutableStateOf(false)
    }
    val currentPercentage = animateFloatAsState(
        targetValue = if (isAnimationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animeDuration,
            delayMillis = animeDelay
        )
    )
    LaunchedEffect(key1 = true) {
        isAnimationPlayed = true
    }
    Box (
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2f)
    ){
        Canvas(modifier = Modifier.size(radius * 2f)) {
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360 * currentPercentage.value,
                useCenter = false,
                style = Stroke(
                    strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }
        Text(
            text = (currentPercentage.value * num).toInt().toString(),
            color = Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}