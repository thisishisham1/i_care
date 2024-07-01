package com.example.icare.core.reusablecomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProgressIndicator(color: Color = MaterialTheme.colorScheme.primary) {
    CircularProgressIndicator(
        modifier = Modifier
            .size(24.dp)
            .background(Color.Transparent),
        color = color,
        strokeWidth = 3.dp
    )
}