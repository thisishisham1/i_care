package com.example.icare.ui.util

sealed class Destinations(val route: String) {
    data object SplashScreen : Destinations("Splash Screen")
    data object OnBoarding : Destinations("OnBoarding")
    data object HomeScreen : Destinations("Home Screen")
    data object SignUp : Destinations("Sign In")
}