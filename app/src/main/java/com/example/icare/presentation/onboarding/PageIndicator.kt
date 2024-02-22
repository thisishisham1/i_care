package com.example.icare.presentation.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.icare.core.theme.gray400
import com.example.icare.core.theme.green500

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pageSize: Int,
    selectedPage: Int,
    selectedColor: Color = green500,
    unselectedColor: Color = gray400
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(2.dp)) {
        repeat(pageSize) { page ->
            Box(
                modifier = modifier
                    .width(15.dp)
                    .height(7.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = if (page == selectedPage) selectedColor else unselectedColor)
            )
        }
    }
}

