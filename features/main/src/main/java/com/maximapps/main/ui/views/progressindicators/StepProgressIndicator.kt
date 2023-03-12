package com.maximapps.main.ui.views.progressindicators

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.maximapps.main.utils.Visibility

@Composable
fun StepProgressIndicator(modifier: Modifier = Modifier, step: Int, totalSteps: Int) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        (1..totalSteps).forEach {
            val alpha: Float by animateFloatAsState(
                if (step >= it) Visibility.Visible else Visibility.Invisible,
                animationSpec = tween(durationMillis = 300)
            )
            Canvas(modifier = Modifier.size(24.dp)) {
                drawCircle(
                    color = Color.White,
                    center = Offset(size.minDimension / 2, size.minDimension / 2),
                    radius = 12f,
                    alpha = alpha
                )
                drawCircle(
                    color = Color.White,
                    center = Offset(size.minDimension / 2, size.minDimension / 2),
                    radius = 12f,
                    style = Stroke(2.dp.toPx())
                )
            }
        }
    }
}
