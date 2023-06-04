package com.maximapps.main.ui.views

import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import com.maximapps.main.ui.views.progressindicators.ProgressIndicator
import java.util.Calendar

@Composable
fun CountdownTimer(
    remainingTime: Long,
    isRunning: Boolean,
    progress: Float,
    alpha: Float,
    clockFaceColors: ClockFaceColors = ClockFaceDefaults.ClockFaceColors
) {
    Box {
        ClockFace(
            modifier = Modifier.align(Alignment.Center),
            calendar = remember { Calendar.getInstance() },
            colors = clockFaceColors,
            transition = rememberInfiniteTransition()
        )
        ProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            progress = progress,
            alpha = alpha
        )

        CountdownView(
            modifier = Modifier.align(BiasAlignment(horizontalBias = 0f, verticalBias = 0.65f)),
            remainingTime = remainingTime,
            isRunning = isRunning
        )
    }
}
