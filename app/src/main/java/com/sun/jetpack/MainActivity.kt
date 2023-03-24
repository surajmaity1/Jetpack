package com.sun.jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val constraintSet = ConstraintSet {
                val blueBox = createRefFor("bluebox")
                val blackBox = createRefFor("blackbox")

                constrain(blueBox) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    height = Dimension.value(150.dp)
                    width = Dimension.value(150.dp)
                }
                constrain(blackBox) {
                    top.linkTo(blueBox.bottom)
                    start.linkTo(parent.start)
                    height = Dimension.value(150.dp)
                    width = Dimension.value(150.dp)
                }
            }
            ConstraintLayout(constraintSet = constraintSet, modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier
                    .background(Color.Blue)
                    .layoutId("bluebox"))
                Box(modifier = Modifier
                    .background(Color.Black)
                    .layoutId("blackbox"))
            }
        }
    }
}