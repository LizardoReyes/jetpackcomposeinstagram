package com.example.jetpackcomposeinstagram

import android.opengl.Visibility
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.SensorDoor
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ColorAnimationSimple() {
    var firstColor by rememberSaveable { mutableStateOf(false) }
    val realColor by animateColorAsState(
        targetValue = if (firstColor) Color.Red else Color.Yellow,
        animationSpec = tween(2000),
        finishedListener = {
            firstColor = !firstColor
        },
        label = "ColorAnimation"
    )

    Box(modifier = Modifier
        .size(100.dp)
        .background(realColor)
        .clickable { firstColor = !firstColor })
}

@Composable
fun SizeAnimation() {
    var smallSize by rememberSaveable { mutableStateOf(true) }
    val size by animateDpAsState(targetValue = if (smallSize) 50.dp else 100.dp,
        animationSpec = tween(500),
        label = "SizeAnimation",
        finishedListener = {
            smallSize = !smallSize
        })

    Box(modifier = Modifier
        .size(size)
        .background(Color.Cyan)
        .clickable { smallSize = !smallSize })
}

@Composable
fun VisibilityAnimation() {
    var isVisibility by rememberSaveable { mutableStateOf(true) }

    Column(Modifier.fillMaxSize()) {
        Button(onClick = { isVisibility = !isVisibility }) {
            Text("Click me")
        }

        Spacer(modifier = Modifier.size(50.dp))

        AnimatedVisibility(
            isVisibility, enter = slideInHorizontally(), exit = slideOutHorizontally()
        ) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(Color.Red)
            )
        }
    }
}

@Preview
@Composable
fun CrossfadeExampleAnimation() {
    var myComponentType: ComponentType by rememberSaveable { mutableStateOf(ComponentType.Text) }

    Column(Modifier.fillMaxSize()) {
        Button(onClick = { myComponentType = getComponentTypeRandom() }) {
            Text("Change Component")
        }

        Crossfade(targetState = myComponentType, label = "Crossfade") { componentType ->
            when (componentType) {
                ComponentType.Image ->
                    Icon(Icons.Default.Favorite, contentDescription = "Favorite")

                ComponentType.Text ->
                    Text("I am a text")

                ComponentType.Box ->
                    Box(modifier = Modifier.size(150.dp).background(Color.Red))

            }

        }
    }
}

fun getComponentTypeRandom(): ComponentType {
    return ComponentType.entries.toTypedArray().random()
}

enum class ComponentType {
    Image, Text, Box
}