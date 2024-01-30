package com.example.icare.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val lightColorScheme = lightColorScheme(
    primary = green500,
    onPrimary = neutralWhite,
    background = neutralWhite,
    error = red500,
    onError = neutralWhite,
    outline = gray400,
    surface = neutralWhite,
    onSurface = black

)

@Composable
fun ICareTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = lightColorScheme,
        shapes = shapes,
        typography = Typography,
        content = content
    )
}