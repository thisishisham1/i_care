package com.example.icare.core.reusablecomponent

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.example.icare.core.Dimens

@Composable
fun WidthSpacer(width: Dp = Dimens.smallPadding) {
    Spacer(modifier = Modifier.width(width))
}
