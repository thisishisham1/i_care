package com.example.icare.model.classes


sealed class Destinations(val route: String) {
    sealed class Main(route: String) : Destinations(route) {
        object Splash : Main("splash")
        object OnBoarding : Main("onBoarding")
        object MainScreen : Main("mainScreen")
        object Offline : Main("offline")
        object TermsAndConditions : Main("termsAndConditions")
    }

    sealed class Auth(route: String) : Destinations(route) {
        object Login : Auth("login")
        object SignUp : Auth("signUp")
        object ForgotPassword : Auth("forgotPassword")
        object Verify : Auth("verify")
        object DoneVerify : Auth("doneVerify")
        object ResetPassword : Auth("resetPassword")
    }

    sealed class Details(route: String) : Destinations(route) {
        object UserDetails : Details("userDetails")
    }

    sealed class Chat(route: String) : Destinations(route) {
        object Conversation : Chat("conversation")
        object ChatBot : Chat("ChatBot")
        object EcgScanner : Chat("Ecg Scanner")
    }

    sealed class Profile(route: String) : Destinations(route) {
        object ProfileDetails : Profile("profileDetails")
        object EditProfile : Profile("editProfile")
        object Notifications : Profile("notifications")
    }

    sealed class Appointment(route: String) : Destinations(route) {
        object BookAppointment : Appointment("bookAppointment")
    }

    sealed class Lists(route: String) : Destinations(route) {
        object Chats : Lists("chats")
        object Doctors : Lists("doctors")
        object Pharmacies : Lists("pharmacies")
        object Labs : Lists("labs")
    }

    sealed class WebView(route: String) : Destinations(route) {
        object WebViewScreen : WebView("webview/{url}")
    }
}