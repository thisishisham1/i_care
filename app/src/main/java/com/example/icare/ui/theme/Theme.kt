package com.example.icare.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val LightColorScheme = lightColorScheme(
    primary = primaryGreen,
    onPrimary = neutralWhite,
    background = neutralWhite,
    error = semanticRed,
    onError = neutralWhite,
    outline = neutralGray,
    surface = Color.White,
    onSurface = Color.Black

)

@Composable
fun ICareTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        shapes = Shapes,
        typography = Typography,
        content = content
    )
}