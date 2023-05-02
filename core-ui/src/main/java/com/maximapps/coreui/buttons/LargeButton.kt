package com.maximapps.coreui.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.maximapps.coreui.theme.Black900
import java.util.Locale

@Composable
fun LargeButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) = Button(
    modifier = modifier,
    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White, contentColor = Black900),
    contentPadding = PaddingValues(16.dp, 12.dp),
    onClick = onClick,
    content = { Text(text.uppercase(Locale.getDefault())) }
)
