package com.example.icare.core.util

sealed class Destinations(val route: String) {
    data object Splash : Destinations("Splash")
    data object OnBoarding : Destinations("OnBoarding")
    data object SelectUser : Destinations("Select User")
    data object SignIn : Destinations("Sign In")

    data object SignUp : Destinations("Sign Up")
    data object Home : Destinations("Home")
    data object ForgotPassword : Destinations("Forgot Password")
    data object Verify : Destinations("Verify")
    data object DoneVerify : Destinations("Done Verify")
    data object ResetPassword : Destinations("Reset Password")
    data object Offline : Destinations("Offline")
}