package com.maximapps.main.ui.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.maximapps.coreui.theme.Black900
import com.maximapps.main.ext.toFormattedTime

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CountdownView(modifier: Modifier = Modifier, remainingTime: Long, isRunning: Boolean) {
    Box(modifier = modifier) {
        AnimatedVisibility(
            visible = isRunning,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Text(
                text = remainingTime.toFormattedTime(),
                fontSize = 28.sp,
                color = Black900
            )
        }
    }
}
