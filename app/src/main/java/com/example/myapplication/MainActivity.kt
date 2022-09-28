package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Box(modifier = Modifier.fillMaxSize()) {

                    Surface(
                        color = MaterialTheme.colors.background,
                    ) {
                        Greeting("Android")
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    var targetValue by remember { mutableStateOf(0f) }
    var alphaTargetValue by remember { mutableStateOf(1f) }

    val value by animateFloatAsState(
        targetValue = targetValue,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500),
            repeatMode = RepeatMode.Reverse
        )
    )
    val alphaValue by animateFloatAsState(
        targetValue = alphaTargetValue,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 300),
            repeatMode = RepeatMode.Reverse
        )
    )
    SideEffect { targetValue = 200f }
    SideEffect { alphaTargetValue = 0f }
    val imageId =
        if (value > 150) R.drawable.ic_baseline_check_circle_24 else R.drawable.ic_baseline_arrow_circle_right_24
    Box(
        modifier = Modifier
            .height(100.dp)
            .width(300.dp)
            .clip(RoundedCornerShape(50))
            .background(androidx.compose.ui.graphics.Color.Green)
    ) {

        Box(
            Modifier
                .offset(value.dp)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = imageId),
                modifier = Modifier
                    .size(100.dp, 100.dp)
                    .clip(CircleShape),
                contentDescription = null,
            )
        }

        Text(
            text = "Бла бла бла",
            modifier = Modifier
                .alpha(alphaValue)
                .align(Alignment.Center),
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}

