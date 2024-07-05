package com.example.icare

import android.content.Context
import android.util.Log
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.icare.model.classes.Destinations
import com.example.icare.model.classes.UsersJson
import com.example.icare.model.sharedPreferences.PreferencesHelper
import com.example.icare.view.TermsAndConditions
import com.example.icare.view.main.MainView
import com.example.icare.view.main.book.BookView
import com.example.icare.view.main.bottomnavitems.home.ChatsView
import com.example.icare.view.main.bottomnavitems.home.NotificationsView
import com.example.icare.view.main.bottomnavitems.home.category.DoctorsView
import com.example.icare.view.main.bottomnavitems.home.category.EcgScanner
import com.example.icare.view.main.bottomnavitems.home.category.LabsView
import com.example.icare.view.main.bottomnavitems.home.category.MedicalImaging
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
    val preferencesHelper = remember {
        PreferencesHelper(context)
    }
    NavHost(
        navController = navController, startDestination = Destinations.Main.Splash.route
    ) {
        composable(Destinations.Main.Splash.route) {
            SplashView(
                navController = navController, preferencesHelper = preferencesHelper
            )
        }
        composable(Destinations.Main.OnBoarding.route) {
            OnBoardingView(navController)
        }
        composable(Destinations.Main.Offline.route) {
            OfflineView()
        }
        composable(Destinations.Chat.EcgScanner.route) {
            EcgScanner(navController = navController)
        }
        composable(Destinations.Chat.CognitiveImaging.route) {
            MedicalImaging(navController = navController)
        }
        composable(Destinations.Main.MainScreen.route) {
            MainView(navController, preferencesHelper)
        }
        composable(Destinations.Auth.SignUp.route) {
            SignUpView(navController = navController)
        }
        composable(Destinations.Auth.Login.route) {
            LoginView(navController, preferencesHelper)
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
        composable("${Destinations.Details.UserDetails.route}/{userId}") { navBackStackEntry ->
            val userId = navBackStackEntry.arguments?.getString("userId")?.toIntOrNull() ?: 0
            val user = navBackStackEntry.savedStateHandle.get<UsersJson>("user")
            Log.d("TestOff", "$user")
            if (user != null) {
                UserDetailsView(userId = userId, user = user, navController = navController)
            } else {
                ErrorSnackbar()
            }
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
            NotificationsView(navController)
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
        composable("${Destinations.WebView.WebViewScreen.route}/{url}") { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url")
            if (url != null) {
                WebViewScreen(url, navController)
            }
        }
    }
}


@Composable
fun ErrorSnackbar() {
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }

    // Simulate setting an error message
    // In a real app, this would be triggered by some error condition
    LaunchedEffect(Unit) {
        errorMessage = "An error occurred"
    }

    // Show the Snackbar when there is an error message
    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            snackbarHostState.showSnackbar(
                message = it,
                duration = SnackbarDuration.Long
            )
            errorMessage = null
        }
    }

    SnackbarHost(hostState = snackbarHostState) { data ->
        Snackbar(snackbarData = data)
    }
}