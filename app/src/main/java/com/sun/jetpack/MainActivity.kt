package com.sun.jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sun.jetpack.ui.theme.JetpackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Use border():
            Column(
                modifier = Modifier
                    .background(Color.Yellow)
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth()
                    .border(width = 4.dp, color = Color.Red)
                    .padding(4.dp)
                    .border(width = 8.dp, color = Color.Green)
                    .padding(8.dp)
                    .border(width = 16.dp, color = Color.Blue)
                    .padding(16.dp)
            ) {
                Text(text = "Hey Coders!",
                    modifier = Modifier
                        .border(2.dp, Color.Black)
                        .padding(10.dp)
                        .offset(x = 20.dp, y = 30.dp)
                        .border(6.dp, Color.Magenta)
                )

                Spacer(modifier = Modifier.height(50.dp))
                Text(text = "Get.. Set.. CODE ->>>>>>>>>>>")
            }
        }
    }
}