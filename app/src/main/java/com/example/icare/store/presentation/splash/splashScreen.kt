package com.example.icare.store.presentation.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.icare.ui.theme.primaryGreen
import com.example.icare.ui.theme.sfFont
import com.example.icare.store.util.Destinations
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 500, easing = {
                OvershootInterpolator(4f).getInterpolation(it)

            })
        )
        delay(2000)
        navController.navigate(Destinations.OnBoarding.route) {
            popUpTo(0)
        }
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) Color(0xff000000) else Color(0xFFFFFFFF))
    ) {

        Text(
            text = "ICARE", style = TextStyle(
                fontSize = 70.sp,
                fontFamily = sfFont,
                fontWeight = FontWeight.SemiBold,
                color = primaryGreen, letterSpacing = (-3).sp
            ), modifier = Modifier.scale(scale.value)
        )
    }
}
