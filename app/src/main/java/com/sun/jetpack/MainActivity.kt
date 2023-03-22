package com.sun.jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fontFamily = FontFamily(
            Font(
                R.font.delicious_handrawn_regular,
                FontWeight.Medium
            )
        )

        setContent{
            Box(modifier = Modifier.fillMaxSize()
                .background(Color(0xFFD4CA8E))
            ){
                Text(
                    text = buildAnnotatedString{
                           withStyle(
                               style = SpanStyle(
                                   color = Color.White,
                                   fontSize = 60.sp
                               )
                           ){
                               append("H")
                           }
                           append("ello")
                            withStyle(
                                style = SpanStyle(
                                    color = Color.White,
                                    fontSize = 60.sp
                                )
                            ){
                                append("E")
                            }
                        append("veryone :)")
                    },
                    color = Color.White,
                    fontSize = 30.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}