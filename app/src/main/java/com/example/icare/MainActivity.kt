package com.example.icare

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.icare.data.PreferencesHelper
import com.example.icare.presentation.home.HomeScreen
import com.example.icare.presentation.onboarding.OnBoardingScreen
import com.example.icare.presentation.registeration.signin.SignInScreen
import com.example.icare.presentation.registeration.signup.SignUpScreen
import com.example.icare.presentation.selectuser.SelectUserScreen
import com.example.icare.presentation.splash.SplashScreen
import com.example.icare.core.theme.ICareTheme
import com.example.icare.core.util.Destinations
import com.example.icare.presentation.offline.OfflineScreen
import com.example.icare.presentation.registeration.forgotpassword.ForgotPasswordScreen
import com.example.icare.presentation.registeration.forgotpassword.ResetPasswordScreen
import com.example.icare.presentation.registeration.verify.DoneVerifyScreen
import com.example.icare.presentation.registeration.verify.VerifyScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ICareTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetupNavigation(this)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val sharedPreferences = PreferencesHelper(this)
        sharedPreferences.getBooleanValue("onBoarding")
    }
}

@Composable
fun SetupNavigation(context: Context) {
    val navController = rememberNavController()
    val preferencesHelper = remember {
        PreferencesHelper(context)
    }
    NavHost(navController = navController, startDestination = Destinations.Offline.route) {
        composable(Destinations.Splash.route) {
            SplashScreen(navController = navController, preferencesHelper = preferencesHelper)
        }
        composable(Destinations.OnBoarding.route) {
            OnBoardingScreen(navController)
        }
        composable(Destinations.SelectUser.route) {
            SelectUserScreen()
        }
        composable(Destinations.SignUp.route) {
            SignUpScreen()
        }
        composable(Destinations.SignIn.route) {
            SignInScreen()
        }
        composable(Destinations.Home.route) {
            HomeScreen()
        }
        composable(Destinations.ForgotPassword.route) {
            ForgotPasswordScreen()
        }
        composable(Destinations.Verify.route) {
            VerifyScreen()
        }
        composable(Destinations.DoneVerify.route) {
            DoneVerifyScreen()
        }
        composable(Destinations.ResetPassword.route) {
            ResetPasswordScreen()
        }
        composable(Destinations.Offline.route) {
            OfflineScreen()
        }
    }
}