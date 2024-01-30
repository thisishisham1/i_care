package com.example.icare.util

sealed class Destinations(val route: String) {
    data object SplashScreen : Destinations("Splash Screen")
    data object OnBoarding : Destinations("OnBoarding")
    data object HomeScreen : Destinations("Home Screen")
    data object SignIn : Destinations("Sign In")
    data object SignUp : Destinations("Sign Up")
}