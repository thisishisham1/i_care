package com.example.icare

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.icare.core.util.Destinations
import com.example.icare.data.PreferencesHelper
import com.example.icare.presentation.offline.OfflineScreen
import com.example.icare.presentation.onboarding.OnBoardingScreen
import com.example.icare.presentation.registeration.forgotpassword.ForgotPasswordScreen
import com.example.icare.presentation.registeration.forgotpassword.ResetPasswordScreen
import com.example.icare.presentation.registeration.signin.SignInScreen
import com.example.icare.presentation.registeration.signup.SignUpScreen
import com.example.icare.presentation.registeration.verify.DoneVerifyScreen
import com.example.icare.presentation.registeration.verify.VerifyScreen
import com.example.icare.presentation.splash.SplashScreen
import com.example.icare.presentation.mainscreen.MainScreen

@Composable
fun MainNavigation(context: Context) {
    val navController = rememberNavController()
    val preferencesHelper = remember {
        PreferencesHelper(context)
    }
    NavHost(navController = navController, startDestination = Destinations.Splash.route) {
        composable(Destinations.Splash.route) {
            SplashScreen(navController = navController, preferencesHelper = preferencesHelper)
        }
        composable(Destinations.OnBoarding.route) {
            OnBoardingScreen(navController)
        }
        composable(Destinations.SignUp.route) {
            SignUpScreen(navController = navController)
        }
        composable(Destinations.SignIn.route) {
            SignInScreen(navController)
        }
        composable(Destinations.ForgotPassword.route) {
            ForgotPasswordScreen(navController)
        }
        composable(Destinations.Verify.route) {
            VerifyScreen(navController)
        }
        composable(Destinations.DoneVerify.route) {
            DoneVerifyScreen(navController)
        }
        composable(Destinations.ResetPassword.route) {
            ResetPasswordScreen(navController)
        }
        composable(Destinations.Offline.route) {
            OfflineScreen()
        }
        composable(Destinations.MainScreen.route) {
            MainScreen()
        }
    }
}