package com.maximapps.main.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.maximapps.coreui.theme.Green700
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun CurrentDateView(
    modifier: Modifier,
    date: Date,
    shape: Shape = RoundedCornerShape(24.dp),
    datePattern: String = "dd/MM/yyyy",
    backgroundColor: Color,
    textColor: Color = Color.White
) {
    Text(
        modifier = modifier
            .graphicsLayer { this.shape = shape; clip = true }
            .background(color = backgroundColor)
            .padding(vertical = 2.dp, horizontal = 24.dp),
        text = SimpleDateFormat(datePattern, Locale.getDefault()).format(date),
        color = textColor,
        textAlign = TextAlign.Center
    )
}
