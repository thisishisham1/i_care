package com.example.icare.core.util

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

val ButtonHeight = 60.dp
val SizeImage = 270.dp

@Composable
fun HeightSpacer(height: Dp = Dimens.smallPadding) {
    Spacer(modifier = Modifier.height(height))
}

@Composable
fun WidthSpacer(width: Dp = Dimens.smallPadding) {
    Spacer(modifier = Modifier.width(width))
}

fun navigateAndClearStack(navController: NavController, destinations: String) {
    navController.navigate(destinations) {
        popUpTo(0)
    }
}