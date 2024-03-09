package com.example.icare.presentation.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.icare.core.util.Destinations
import com.example.icare.data.PreferencesHelper
import kotlinx.coroutines.delay

class SplashViewModel(private val preferencesHelper: PreferencesHelper) : ViewModel() {
    private val _animationDuration = 1000
    private val _delayDuration = 2000L
    val scale = Animatable(0f)

    suspend fun animateScale(scale: Animatable<Float, AnimationVector1D>) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = _animationDuration, easing = {
                OvershootInterpolator(2f).getInterpolation(it)

            })
        )
    }

    suspend fun delayAndNavigate(
        navController: NavController
    ) {
        delay(_delayDuration)
        navigate(preferencesHelper, navController)
    }

    private fun navigate(preferencesHelper: PreferencesHelper, navController: NavController) {
        val destinations = if (this.preferencesHelper.getBooleanValue("onBoarding")) {
            Destinations.SignIn.route
        } else Destinations.OnBoarding.route
        navController.navigate(destinations) {
            popUpTo(0)
        }
    }

}