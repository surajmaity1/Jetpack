package com.sun.jetpack

import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box (
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF4251A7))
                    .padding(8.dp)
            ){
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .border(5.dp, Color.White, RoundedCornerShape(10.dp))
                        .padding(25.dp)
                    ) {
                    var volume by remember {
                        mutableStateOf(0f)
                    }
                    val barCount = 15
                    MusicButton(
                        modifier = Modifier.size(100.dp)
                    ) {
                        volume = it
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    VolumeIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        activeBars = (barCount * volume).roundToInt(),
                        barCount = barCount
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MusicButton(
    modifier: Modifier = Modifier,
    leastAngle: Float = 15f,
    onValueChange: (Float) -> Unit
) {
    var rotation by remember {
        mutableStateOf(leastAngle)
    }
    var touchPositionX by remember {
        mutableStateOf(0f)
    }
    var touchPositionY by remember {
        mutableStateOf(0f)
    }
    var centerPosX by remember {
        mutableStateOf(0f)
    }
    var centerPosY by remember {
        mutableStateOf(0f)
    }

    Image(
        painter = painterResource(id = R.drawable.music_button),
        contentDescription = "Music Button",
        modifier = modifier
            .fillMaxSize()
            .onGloballyPositioned {
                val windowBoundSize = it.boundsInWindow().size
                centerPosX = windowBoundSize.width / 2f
                centerPosY = windowBoundSize.height / 2f
            }
            .pointerInteropFilter { motionEvent ->
                touchPositionX = motionEvent.x
                touchPositionY = motionEvent.y

                val angle = -atan2(
                    centerPosX - touchPositionX,
                    centerPosY - touchPositionY
                ) * (180f / PI).toFloat()

                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN,
                    MotionEvent.ACTION_MOVE -> {
                        if (angle !in -leastAngle..leastAngle) {
                            val fixAngle = if (angle in -180f..-leastAngle) {
                                360f + angle
                            } else {
                                angle
                            }
                            rotation = fixAngle

                            val percent = (fixAngle - leastAngle) / (360 - 2 * leastAngle)
                            onValueChange(percent)
                            true
                        } else false
                    }
                    else -> false
                }
            }
            .rotate(rotation)
    )
}

@Composable
fun VolumeIndicator(
    modifier: Modifier = Modifier,
    activeBars: Int = 0,
    barCount: Int = 10
) {
    BoxWithConstraints (
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        val barWidth = remember {
            constraints.maxWidth / (2f * barCount)
        }
        Canvas(modifier = modifier) {
            for (i in 0 until barCount) {
                drawRoundRect(
                    color = if (i in 0..activeBars) Color.Red else Color.LightGray,
                    topLeft = Offset(i * barWidth * 2f + barWidth/ 2f, 0f),
                    size = Size(barWidth, constraints.maxHeight.toFloat()),
                    cornerRadius = CornerRadius(0f)
                )
            }
        }
    }
}