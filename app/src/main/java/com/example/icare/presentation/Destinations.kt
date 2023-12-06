package com.example.icare.presentation

sealed class Destinations(val route: String) {
    object SplashScreen : Destinations("Splash Screen")
    object OnBoarding : Destinations("OnBoarding")
}