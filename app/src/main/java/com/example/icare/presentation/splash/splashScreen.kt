package com.example.icare.presentation.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
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
import com.example.icare.data.PreferencesHelper
import com.example.icare.core.util.Destinations
import com.example.icare.core.theme.green500
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController, preferencesHelper: PreferencesHelper) {
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        animateScale(scale)
        delayAndNavigate(preferencesHelper, navController)
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(green500)
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


private suspend fun animateScale(scale: Animatable<Float, AnimationVector1D>) {
    scale.animateTo(
        targetValue = 1f,
        animationSpec = tween(durationMillis = ANIMATION_DURATION, easing = {
            OvershootInterpolator(2f).getInterpolation(it)

        })
    )
}

private suspend fun delayAndNavigate(
    preferencesHelper: PreferencesHelper,
    navController: NavController
) {
    delay(DELAY_DURATION)
    navigate(preferencesHelper, navController)
}

private fun navigate(preferencesHelper: PreferencesHelper, navController: NavController) {
    val destinations = if (preferencesHelper.getBooleanValue("onBoarding")) {
        Destinations.SignIn.route
    } else Destinations.OnBoarding.route
    navController.navigate(destinations) {
        popUpTo(0)
    }
}

private const val ANIMATION_DURATION = 1000
private const val DELAY_DURATION = 2000L