package com.example.icare.core.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val lightColorScheme = lightColorScheme(
    primary = green500,
    onPrimary = neutralWhite, primaryContainer = green200, // Added for container backgrounds
    onPrimaryContainer = black, // Added for text on container backgrounds
    secondary = green700, // Added for secondary elements
    onSecondary = neutralWhite,
    secondaryContainer = green200.copy(alpha = 0.8f), // Added for secondary container backgrounds
    onSecondaryContainer = black, // Added for text on secondary container backgrounds
    background = neutralWhite,
    onBackground = black,
    surface = neutralWhite,
    onSurface = black,
    error = red500,
    onError = neutralWhite,
    errorContainer = red500.copy(alpha = 0.1f), // Added for error container backgrounds
    onErrorContainer = neutralWhite, // Added for text on error container backgrounds
    outline = gray400,
    surfaceVariant = gray400.copy(alpha = 0.2f), // Added for subtle surface variations
    onSurfaceVariant = gray600 // Added for text on surface variants
)

@Composable
fun ICareTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = lightColorScheme,
        shapes = shapes,
        typography = AppTypography,
        content = content
    )
}