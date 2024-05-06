package com.example.icare

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.icare.model.classes.Destinations
import com.example.icare.model.sharedPreferences.PreferencesHelper
import com.example.icare.view.TermsAndConditions
import com.example.icare.view.main.MainView
import com.example.icare.view.main.book.BookView
import com.example.icare.view.main.bottomnavitems.home.ChatsView
import com.example.icare.view.main.bottomnavitems.home.NotificationsView
import com.example.icare.view.main.bottomnavitems.home.category.DoctorsView
import com.example.icare.view.main.bottomnavitems.home.category.LabsView
import com.example.icare.view.main.bottomnavitems.home.category.PharmaciesView
import com.example.icare.view.main.bottomnavitems.home.category.chatbot.ChatBotView
import com.example.icare.view.main.bottomnavitems.profile.edit.EditProfileView
import com.example.icare.view.main.chat.ChatView
import com.example.icare.view.main.details.DoctorDetailsView
import com.example.icare.view.main.details.LabDetailsView
import com.example.icare.view.main.details.PharmacyDetailsView
import com.example.icare.view.offline.OfflineView
import com.example.icare.view.onboarding.OnBoardingView
import com.example.icare.view.registeration.forgotpassword.ForgotPasswordVeiw
import com.example.icare.view.registeration.forgotpassword.ResetPasswordView
import com.example.icare.view.registeration.login.LoginView
import com.example.icare.view.registeration.signup.SignUpView
import com.example.icare.view.registeration.verify.DoneVerifyView
import com.example.icare.view.registeration.verify.VerifyView
import com.example.icare.view.splash.SplashView

@Composable
fun MainNavigation(context: Context) {
    val navController = rememberNavController()
    val preferencesHelper = remember {
        PreferencesHelper(context)
    }
    NavHost(
        navController = navController,
        startDestination = Destinations.Main.TermsAndConditions.route
    ) {
        composable(Destinations.Main.Splash.route) {
            SplashView(
                navController = navController,
                preferencesHelper = preferencesHelper
            )
        }
        composable(Destinations.Main.OnBoarding.route) {
            OnBoardingView(navController)
        }
        composable(Destinations.Main.Offline.route) {
            OfflineView()
        }
        composable(Destinations.Main.MainScreen.route) {
            MainView(navController)
        }
        composable(Destinations.Auth.SignUp.route) {
            SignUpView(navController = navController)
        }
        composable(Destinations.Auth.Login.route) {
            LoginView(navController)
        }
        composable(Destinations.Auth.ForgotPassword.route) {
            ForgotPasswordVeiw(navController)
        }
        composable(Destinations.Auth.Verify.route) {
            VerifyView(navController)
        }
        composable(Destinations.Auth.DoneVerify.route) {
            DoneVerifyView()
        }
        composable(Destinations.Auth.ResetPassword.route) {
            ResetPasswordView(navController)
        }
        composable("${Destinations.Details.DoctorDetails.route}/{doctorId}") { navBackStackEntry ->
            val doctorId = navBackStackEntry.arguments?.getString("doctorId")?.toIntOrNull() ?: 0
            DoctorDetailsView(doctorId = doctorId, navController)
        }
        composable("${Destinations.Details.PharmacyDetails.route}/{pharmacyId}") {
            val pharmacyId =
                it.arguments?.getString("pharmacyId")?.toIntOrNull() ?: 0
            PharmacyDetailsView(pharmacyId = pharmacyId, navController)
        }
        composable("${Destinations.Details.LabDetails.route}/{labId}") {
            val labId = it.arguments?.getString("labId")?.toIntOrNull() ?: 0
            LabDetailsView(labId = labId, navController)
        }
        composable(Destinations.Appointment.BookAppointment.route) {
            BookView(navController)
        }
        composable(Destinations.Chat.Conversation.route) { ChatView(navController) }
        composable(Destinations.Chat.ChatBot.route) { ChatBotView(navController) }
        composable(Destinations.Profile.EditProfile.route) {
            EditProfileView(navController)
        }
        composable(Destinations.Profile.Notifications.route) {
            NotificationsView()
        }
        composable(Destinations.Lists.Chats.route) {
            ChatsView()
        }
        composable(Destinations.Lists.Doctors.route) {
            DoctorsView(navController = navController)
        }
        composable(Destinations.Lists.Pharmacies.route) {
            PharmaciesView(navController = navController)
        }
        composable(Destinations.Lists.Labs.route) {
            LabsView(navController)
        }
        composable(Destinations.Main.TermsAndConditions.route) {
            TermsAndConditions(navController)
        }
    }
}