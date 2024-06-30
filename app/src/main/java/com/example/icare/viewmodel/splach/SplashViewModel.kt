package com.example.icare.viewmodel.splach

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.icare.MyApplication
import com.example.icare.model.classes.Destinations
import com.example.icare.model.local.UserDatabase
import com.example.icare.model.sharedPreferences.PreferencesHelper
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
        navigate(navController)
    }

    private suspend fun navigate(navController: NavController) {
        val userDao = UserDatabase.getDatabase(MyApplication.applicationContext()).userResponseDao()
        val userCount = userDao.getUserCount()
        val destinations =
            if (this.preferencesHelper.getBooleanValue("onBoarding")) {
                if (userCount > 0) {
                    Destinations.Main.MainScreen.route
                } else
                    Destinations.Auth.Login.route
            } else Destinations.Main.OnBoarding.route
        navController.navigate(destinations) {
            popUpTo(0)
        }
    }

}