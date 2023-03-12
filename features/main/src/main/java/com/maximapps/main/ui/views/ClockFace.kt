package com.maximapps.main.ui.views

import android.text.format.DateUtils
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.maximapps.coreui.theme.Black900
import com.maximapps.coreui.theme.Green200
import com.maximapps.main.ext.hoursInDegree
import com.maximapps.main.ext.minutesInDegree
import com.maximapps.main.ext.secondsInDegree
import com.maximapps.main.utils.Tau
import java.util.Calendar

object ClockFaceDefaults {
    /**
     *
     */
    val BezelWidth: Dp = 12.dp

    /**
     *
     */
    val ClockMovementRadius = 8.dp

    /**
     *
     */
    val MarkerValues = MarkerValues(8.dp, 15.dp)

    /**
     *
     */
    val ClockFaceColors = ClockFaceColors(
        background = Color.White,
        markersColor = Black900,
        bezelColor = Green200,
        handsColors = ClockHandsColors()
    )
}

/**
 *
 */
data class ClockFaceColors(
    val background: Color,
    val markersColor: Color,
    val bezelColor: Color,
    val handsColors: ClockHandsColors
)

/**
 *
 */
data class ClockHandsColors(
    val primary: Color = Black900,
    val secondary: Color = Color.Red
)

/**
 *
 */
data class MarkerValues(val width: Dp, val length: Dp)

/**
 *
 */
@Composable
fun ClockFace(
    modifier: Modifier = Modifier,
    calendar: Calendar,
    transition: InfiniteTransition,
    movementRadius: Dp = ClockFaceDefaults.ClockMovementRadius,
    bezelWidth: Dp = ClockFaceDefaults.BezelWidth,
    markerValues: MarkerValues = ClockFaceDefaults.MarkerValues,
    colors: ClockFaceColors = ClockFaceDefaults.ClockFaceColors
) {
    LaunchedEffect(Unit) {
        calendar.timeInMillis = System.currentTimeMillis()
    }

    val hourHandRotation by transition.animateClockHand(
        angle = calendar.hoursInDegree,
        durationMillis = DateUtils.DAY_IN_MILLIS / 2
    )
    val minuteHandRotation by transition.animateClockHand(
        angle = calendar.minutesInDegree,
        durationMillis = DateUtils.HOUR_IN_MILLIS
    )
    val secondHandRotation by transition.animateClockHand(
        angle = calendar.secondsInDegree,
        durationMillis = DateUtils.MINUTE_IN_MILLIS
    )

    Canvas(
        modifier = modifier
            .size(240.dp)
            .aspectRatio(1.0f)
    ) {
        drawDial(markerValues, colors)
        drawClockHands(hourHandRotation, minuteHandRotation, secondHandRotation, colors.handsColors)
        drawBezel(bezelWidth, colors.bezelColor)
        drawClockMovement(radius = movementRadius, color = colors.handsColors.primary)
    }
}

/**
 *
 */
@Composable
private fun InfiniteTransition.animateClockHand(
    angle: Float,
    durationMillis: Long,
) = animateFloat(
    initialValue = angle,
    targetValue = angle + Tau().toDegrees(),
    animationSpec = infiniteRepeatable(
        animation = tween(durationMillis.toInt(), easing = LinearEasing)
    )
)

/**
 *
 */
private fun DrawScope.drawDial(markerValues: MarkerValues, colors: ClockFaceColors) {
    drawCircle(
        color = colors.background,
        center = Offset(size.minDimension / 2, size.minDimension / 2),
        radius = size.minDimension / 2
    )
    drawHourMarker(
        color = colors.markersColor,
        width = markerValues.width.toPx(),
        start = Offset(size.minDimension / 2, 0f),
        end = Offset(size.minDimension / 2, markerValues.length.toPx())
    )
    drawHourMarker(
        color = colors.markersColor,
        width = markerValues.width.toPx(),
        start = Offset(0f, size.minDimension / 2),
        end = Offset(markerValues.length.toPx(), size.minDimension / 2)
    )
    drawHourMarker(
        color = colors.markersColor,
        width = markerValues.width.toPx(),
        start = Offset(size.minDimension, size.minDimension / 2),
        end = Offset(size.minDimension - markerValues.length.toPx(), size.minDimension / 2)
    )
    drawHourMarker(
        color = colors.markersColor,
        width = markerValues.width.toPx(),
        start = Offset(size.minDimension / 2, size.minDimension),
        end = Offset(size.minDimension / 2, size.minDimension - markerValues.length.toPx())
    )
}

/**
 *
 */
private fun DrawScope.drawClockHands(
    hourHandRotation: Float,
    minuteHandRotation: Float,
    secondHandRotation: Float,
    handsColors: ClockHandsColors
) {
    drawHand(
        handRotation = secondHandRotation,
        width = 2.dp,
        end = Offset(size.minDimension / 2, 32.dp.toPx()),
        color = handsColors.secondary
    )
    drawHand(
        handRotation = minuteHandRotation,
        width = 4.dp,
        end = Offset(size.minDimension / 2, 48.dp.toPx()),
        color = handsColors.primary
    )
    drawHand(
        handRotation = hourHandRotation,
        width = 6.dp,
        end = Offset(size.minDimension / 2, 84.dp.toPx()),
        color = handsColors.primary
    )
}

/**
 *
 */
private fun DrawScope.drawHand(
    handRotation: Float,
    width: Dp,
    start: Offset = Offset(size.minDimension / 2, size.minDimension / 2),
    end: Offset,
    color: Color
) = withTransform({ rotate(handRotation, start) }) {
    drawLine(
        strokeWidth = width.toPx(),
        cap = StrokeCap.Square,
        color = color,
        start = start,
        end = end
    )
}

/**
 *
 */
private fun DrawScope.drawHourMarker(color: Color, width: Float, start: Offset, end: Offset) =
    drawLine(
        strokeWidth = width,
        cap = StrokeCap.Square,
        color = color,
        start = start,
        end = end
    )

/**
 *
 */
private fun DrawScope.drawBezel(width: Dp, color: Color) = drawCircle(
    color = color,
    center = Offset(size.minDimension / 2, size.minDimension / 2),
    radius = size.minDimension / 2,
    style = Stroke(width.toPx())
)

/**
 *
 */
private fun DrawScope.drawClockMovement(radius: Dp, color: Color) = drawCircle(
    color = color,
    center = Offset(size.minDimension / 2, size.minDimension / 2),
    radius = radius.toPx(),
)
