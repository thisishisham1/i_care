package com.example.icare

sealed class Destinations(val route: String) {
    object SplashScreen : Destinations("Splash Screen")
    object OnBoarding : Destinations("OnBoarding")
}