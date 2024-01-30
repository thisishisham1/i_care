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
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.icare.data.PreferencesHelper
import com.example.icare.ui.home.HomeScreen
import com.example.icare.ui.onboarding.OnBoardingScreen
import com.example.icare.ui.registeration.signin.SignIn
import com.example.icare.ui.registeration.signup.SignUp
import com.example.icare.ui.splash.SplashScreen
import com.example.icare.ui.theme.ICareTheme
import com.example.icare.util.Destinations

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
    NavHost(navController = navController, startDestination = Destinations.SplashScreen.route) {
        composable(Destinations.SplashScreen.route) {
            SplashScreen(navController = navController, preferencesHelper = preferencesHelper)
        }
        composable(Destinations.OnBoarding.route) {
            OnBoardingScreen(navController)
        }
        composable(Destinations.HomeScreen.route) {
            HomeScreen()
        }

        composable(Destinations.SignUp.route) {
            SignUp()
        }
        composable(Destinations.SignIn.route) {
            SignIn()
        }
    }
}

