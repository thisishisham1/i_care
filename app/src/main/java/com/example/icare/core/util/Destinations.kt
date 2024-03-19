package com.example.icare.core.util

import androidx.navigation.NavDestination

sealed class Destinations(val route: String) {
    data object Splash : Destinations("Splash")
    data object OnBoarding : Destinations("OnBoarding")
    data object SignIn : Destinations("Sign In")

    data object SignUp : Destinations("Sign Up")
    data object ForgotPassword : Destinations("Forgot Password")
    data object Verify : Destinations("Verify")
    data object DoneVerify : Destinations("Done Verify")
    data object ResetPassword : Destinations("Reset Password")
    data object Offline : Destinations("Offline")
    data object MainScreen : Destinations("Main Screen")
    data object DoctorDetails : Destinations("Doctor Details")
    data object Home : Destinations("Home")
    data object Search : Destinations("Search")
    data object Appointment : Destinations("Appointment")
    data object Profile : Destinations("Profile")
}