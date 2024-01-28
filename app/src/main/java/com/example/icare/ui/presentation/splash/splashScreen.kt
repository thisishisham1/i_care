package com.example.icare.ui.presentation.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.icare.R
import com.example.icare.ui.util.Destinations
import com.example.icare.ui.theme.primaryGreen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000, easing = {
                OvershootInterpolator(2f).getInterpolation(it)

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
            .background(primaryGreen)
    ) {
        AsyncImage(
            model = R.drawable.logo,
            contentDescription = "",
            Modifier
                .requiredSize(250.dp)
                .scale(scale.value)
        )
    }
}
