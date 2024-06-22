package com.example.icare.core.reusablecomponent

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.icare.core.theme.green500

@Composable
fun ProgressIndicator(color: Color = green500) {
    CircularProgressIndicator(
        modifier = Modifier.size(15.dp), color = color, strokeWidth = 3.dp
    )
}