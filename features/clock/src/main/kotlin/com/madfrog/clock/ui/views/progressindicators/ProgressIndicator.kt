package com.maximapps.main.ui.views.progressindicators

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.maximapps.coreui.theme.Green700
import com.maximapps.main.utils.Tau
import kotlin.math.cos
import kotlin.math.sin

object ProgressIndicatorDefaults {
    val HandleWidth = 24.dp
    val IndicatorWidth = 12.dp
    val ProgressIndicatorColors = ProgressIndicatorColors(Green700)
}

data class ProgressIndicatorColors(val primary: Color)

@Composable
fun ProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Float,
    alpha: Float = 1f,
    handleWidth: Dp = ProgressIndicatorDefaults.HandleWidth,
    indicatorWidth: Dp = ProgressIndicatorDefaults.IndicatorWidth,
    colors: ProgressIndicatorColors = ProgressIndicatorDefaults.ProgressIndicatorColors
) {
    Canvas(
        modifier
            .size(240.dp)
            .alpha(alpha)
            .aspectRatio(1.0f)
    ) {
        drawArc(
            color = colors.primary,
            startAngle = -89f,
            sweepAngle = progress,
            useCenter = false,
            style = Stroke(indicatorWidth.toPx(), cap = StrokeCap.Square)
        )
        drawPoints(
            listOf(calculatePointOnCircle(progress)),
            pointMode = PointMode.Points,
            color = colors.primary,
            strokeWidth = handleWidth.toPx(),
            cap = StrokeCap.Round
        )
    }
}

@Composable
fun animateProgressAsState(remainingTime: Long, totalTime: Long, duration: Int = 500) =
    animateFloatAsState(
        targetValue = with(Tau()) {
            when {
                remainingTime < 0 -> 0f
                else -> toDegrees() - ((toDegrees() / totalTime) * (totalTime - remainingTime))
            }
        },
        animationSpec = tween(duration, easing = LinearEasing)
    )

private fun DrawScope.calculatePointOnCircle(angle: Float, handleOffset: Float = 270f): Offset {
    val center = Offset(size.minDimension / 2f, size.minDimension / 2f)
    val beta = (angle + handleOffset) * Math.toRadians(1.0).toFloat()
    val radius = size.minDimension / 2f
    return Offset(center.x + cos(beta) * radius, center.y + sin(beta) * radius)
}
