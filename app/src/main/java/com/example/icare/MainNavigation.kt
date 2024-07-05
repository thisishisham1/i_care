package com.example.icare

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.icare.model.classes.Destinations
import com.example.icare.model.sharedPreferences.PreferencesHelper
import com.example.icare.repository.UsersRepository
import com.example.icare.view.TermsAndConditions
import com.example.icare.view.main.MainView
import com.example.icare.view.main.book.BookView
import com.example.icare.view.main.bottomnavitems.home.ChatsView
import com.example.icare.view.main.bottomnavitems.home.NotificationsView
import com.example.icare.view.main.bottomnavitems.home.category.DoctorsView
import com.example.icare.view.main.bottomnavitems.home.category.EcgScanner
import com.example.icare.view.main.bottomnavitems.home.category.LabsView
import com.example.icare.view.main.bottomnavitems.home.category.PharmaciesView
import com.example.icare.view.main.bottomnavitems.home.category.WebViewScreen
import com.example.icare.view.main.bottomnavitems.home.category.chatbot.ChatBotView
import com.example.icare.view.main.bottomnavitems.profile.edit.EditProfileView
import com.example.icare.view.main.chat.ChatView
import com.example.icare.view.main.details.UserDetailsView
import com.example.icare.view.offline.OfflineView
import com.example.icare.view.onboarding.OnBoardingView
import com.example.icare.view.registeration.forgotpassword.ForgotPasswordView
import com.example.icare.view.registeration.forgotpassword.ResetPasswordView
import com.example.icare.view.registeration.login.LoginView
import com.example.icare.view.registeration.signup.SignUpView
import com.example.icare.view.registeration.verify.DoneVerifyView
import com.example.icare.view.registeration.verify.VerifyView
import com.example.icare.view.splash.SplashView

@Composable
fun MainNavigation(context: Context) {
    val navController = rememberNavController()
    val preferencesHelper = remember { PreferencesHelper(context) }
    val mainViewModel = remember { MainViewModel(UsersRepository()) }

    NavHost(navController = navController, startDestination = Destinations.Main.MainScreen.route) {
        // Authentication screens
        composable(Destinations.Auth.Login.route) {
            LoginView(navController, preferencesHelper)
        }
        composable(Destinations.Auth.SignUp.route) {
            SignUpView(navController)
        }
        composable(Destinations.Auth.ForgotPassword.route) {
            ForgotPasswordView(navController)
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
        // Main screens
        composable(Destinations.Main.Splash.route) {
            SplashView(navController, preferencesHelper)
        }
        composable(Destinations.Main.OnBoarding.route) {
            OnBoardingView(navController)
        }
        composable(Destinations.Main.Offline.route) {
            OfflineView()
        }
        composable(Destinations.Main.MainScreen.route) {
            MainView(navController, preferencesHelper, mainViewModel)
        }
        composable(Destinations.Main.TermsAndConditions.route) {
            TermsAndConditions(navController)
        }
        // Details screen
        composable("${Destinations.Details.UserDetails.route}/{userId}") { navBackStackEntry ->
            val userId = navBackStackEntry.arguments?.getString("userId")?.toIntOrNull() ?: 0
            UserDetailsView(userId, navController, mainViewModel)
        }
        // Appointment screen
        composable(Destinations.Appointment.BookAppointment.route) {
            BookView(navController)
        }

        // Chat screens
        composable(Destinations.Chat.Conversation.route) {
            ChatView(navController)
        }
        composable(Destinations.Chat.ChatBot.route) {
            ChatBotView(navController)
        }
        composable(Destinations.Chat.EcgScanner.route) {
            EcgScanner(navController)
        }

        // Profile screens
        composable(Destinations.Profile.EditProfile.route) {
            EditProfileView(navController)
        }
        composable(Destinations.Profile.Notifications.route) {
            NotificationsView(navController)
        }

        // Lists screens
        composable(Destinations.Lists.Chats.route) {
            ChatsView()
        }
        composable(Destinations.Lists.Doctors.route) {
            DoctorsView(navController, mainViewModel)
        }
        composable(Destinations.Lists.Pharmacies.route) {
            PharmaciesView(navController, mainViewModel)
        }
        composable(Destinations.Lists.Labs.route) {
            LabsView(navController, mainViewModel)
        }

        // Web View screen
        composable("${Destinations.WebView.WebViewScreen.route}/{url}") { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url")
            if (url != null) {
                WebViewScreen(url, navController)
            }
        }
    }
}