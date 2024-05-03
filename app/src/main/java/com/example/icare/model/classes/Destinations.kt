package com.example.icare.model.classes


sealed class Destinations(val route: String) {
    sealed class Main(route: String) : Destinations(route) {
        data object Splash : Main("splash")
        data object OnBoarding : Main("onBoarding")
        data object MainScreen : Main("mainScreen")
        data object Offline : Main("offline")
    }

    sealed class Auth(route: String) : Destinations(route) {
        data object Login : Auth("login")
        data object SignUp : Auth("signUp")
        data object ForgotPassword : Auth("forgotPassword")
        data object Verify : Auth("verify")
        data object DoneVerify : Auth("doneVerify")
        data object ResetPassword : Auth("resetPassword")
    }

    sealed class Details(route: String) : Destinations(route) {
        object LabDetails : Details("labDetails")
        data object DoctorDetails : Details("doctorDetails")
        data object PharmacyDetails : Details("pharmacyDetails")
    }

    sealed class Chat(route: String) : Destinations(route) {
        data object Conversation : Destinations("conversation")
        data object ChatBot : Destinations("ChatBot")

    }

    sealed class Profile(route: String) : Destinations(route) {
        data object ProfileDetails : Details("profileDetails")
        data object EditProfile : Profile("editProfile")
        data object Notifications : Profile("notifications")

    }

    sealed class Appointment(route: String) : Destinations(route) {
        data object BookAppointment : Appointment("bookAppointment")

    }

    sealed class Lists(route: String) : Destinations(route) {
        data object Chats : Lists("chats")
        data object Doctors : Lists("doctors")
        data object Pharmacies : Lists("pharmacies")
        data object Labs : Lists("labs")
    }

}