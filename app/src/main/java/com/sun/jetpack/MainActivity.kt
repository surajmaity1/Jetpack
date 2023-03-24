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
import androidx.constraintlayout.compose.ChainStyle
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
                val guidelineFromTop = createGuidelineFromTop(0.7f)

                constrain(blueBox) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    height = Dimension.value(150.dp)
                    width = Dimension.value(150.dp)
                }
                constrain(blackBox) {
                    top.linkTo(guidelineFromTop)
                    start.linkTo(parent.start)
                    // end.linkTo(parent.end)

                    height = Dimension.value(150.dp)
                    width = Dimension.value(150.dp)
                    // width = Dimension.fillToConstraints
                }
                createHorizontalChain(blueBox, blackBox, chainStyle = ChainStyle.SpreadInside)
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