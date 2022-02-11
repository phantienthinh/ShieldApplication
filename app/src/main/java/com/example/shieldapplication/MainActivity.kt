package com.example.shieldapplication

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.shieldapplication.ui.theme.ShieldApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShieldApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    val infinityTransition = rememberInfiniteTransition()
//                    val animationScale by infinityTransition.animateFloat(
//                        initialValue = 0f,
//                        targetValue = 1f,
//                        animationSpec = infiniteRepeatable(
//                            animation = tween(1000),
//                            repeatMode = RepeatMode.Restart
//                        )
//                    )
                    Shield(
                        process = 0f,
                        scanColor = Color.BLACK.hashCode(),
                        repeatDuration = 1000
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ShieldApplicationTheme {
        Greeting("Android")
    }
}