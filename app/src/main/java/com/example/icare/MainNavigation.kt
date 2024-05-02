package com.example.icare

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.icare.core.util.Destinations
import com.example.icare.model.sharedPreferences.PreferencesHelper
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
    NavHost(navController = navController, startDestination = Destinations.MainScreen.route) {
        composable(Destinations.Splash.route) {
            SplashView(navController = navController, preferencesHelper = preferencesHelper)
        }
        composable(Destinations.OnBoarding.route) {
            OnBoardingView(navController)
        }
        composable(Destinations.SignUp.route) {
            SignUpView(navController = navController)
        }
        composable(Destinations.Login.route) {
            LoginView(navController)
        }
        composable(Destinations.ForgotPassword.route) {
            ForgotPasswordVeiw(navController)
        }
        composable(Destinations.Verify.route) {
            VerifyView(navController)
        }
        composable(Destinations.DoneVerify.route) {
            DoneVerifyView()
        }
        composable(Destinations.ResetPassword.route) {
            ResetPasswordView(navController)
        }
        composable(Destinations.Offline.route) {
            OfflineView()
        }
        composable(Destinations.MainScreen.route) {
            MainView(navController)
        }
        composable("${Destinations.DoctorDetails.route}/{doctorId}") { navBackStackEntry ->
            val doctorId = navBackStackEntry.arguments?.getString("doctorId")?.toIntOrNull() ?: 0
            DoctorDetailsView(doctorId = doctorId, navController)
        }
        composable("${Destinations.PharmacyDetails.route}/{pharmacyId}") { NavBackStackEntry ->
            val pharmacyId =
                NavBackStackEntry.arguments?.getString("pharmacyId")?.toIntOrNull() ?: 0
            PharmacyDetailsView(pharmacyId = pharmacyId, navController)
        }
        composable("${Destinations.LabDetails.route}/{labId}") { NavBackStackEntry ->
            val labId = NavBackStackEntry.arguments?.getString("labId")?.toIntOrNull() ?: 0
            LabDetailsView(labId = labId, navController)
        }
        composable(Destinations.BookAppointment.route) {
            BookView(navController)
        }
        composable(Destinations.Chat.route) { ChatView(navController) }
        composable(Destinations.ChatBot.route) { ChatBotView() }
        composable(Destinations.EditProfile.route) {
            EditProfileView(navController)
        }
        composable(Destinations.Chats.route) {
            ChatsView()
        }
        composable(Destinations.Notifications.route) {
            NotificationsView()
        }
        composable(Destinations.Doctors.route) {
            DoctorsView(navController = navController)
        }
        composable(Destinations.Pharmacies.route) {
            PharmaciesView(navController = navController)
        }
        composable(Destinations.Labs.route) {
            LabsView(navController)
        }
    }
}