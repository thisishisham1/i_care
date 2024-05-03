package com.example.icare.core.util.reusablecomponent

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.example.icare.core.util.Dimens

@Composable
fun HeightSpacer(height: Dp = Dimens.smallPadding) {
    Spacer(modifier = Modifier.height(height))
}
