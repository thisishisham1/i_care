package com.example.icare.util

sealed class Destinations(val route: String) {
    object SplashScreen : Destinations("Splash Screen")
    object OnBoarding : Destinations("OnBoarding")
    object HomeScreen : Destinations("Home Screen")
}