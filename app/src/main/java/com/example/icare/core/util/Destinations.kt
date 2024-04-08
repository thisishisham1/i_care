package com.example.icare.core.util


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
    data object PharmacyDetails : Destinations("Pharmacy Details")
    data object LabDetails : Destinations("Lab Details")
    data object BookAppointment : Destinations("Book Appointment")
    data object Chat : Destinations("Chat screen")
    data object ChatBot : Destinations("ChatBot")
    data object EditProfile : Destinations("Edit Profile")
    data object Notifications : Destinations("Notifications")
    data object Chats : Destinations("Chats")
    data object Doctors : Destinations("Doctors")
    data object Pharmacies : Destinations("Pharmacies")
    data object Labs : Destinations("Labs")
}