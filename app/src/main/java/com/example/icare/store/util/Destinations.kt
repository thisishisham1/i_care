package com.example.icare.store.util

sealed class Destinations(val route: String) {
    object SplashScreen : Destinations("Splash Screen")
    object OnBoarding : Destinations("OnBoarding")
    object HomeScreen : Destinations("Home Screen")
}