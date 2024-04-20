package com.example.newsapp.core.composables

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import com.example.newsapp.R
import androidx.compose.ui.res.colorResource


fun Modifier.shimmered() = composed {

    val transition = rememberInfiniteTransition()
    val alpha = transition.animateFloat(
        initialValue = 0.2f, targetValue = 0.9f, animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        )
    ).value

    this.then(
        Modifier.background(color = colorResource(id = R.color.shimmer).copy(alpha = alpha))

    )
}



