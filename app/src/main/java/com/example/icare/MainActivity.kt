package com.example.icare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.icare.data.PreferencesHelper
import com.example.icare.ui.presentation.home.HomeScreen
import com.example.icare.ui.presentation.onboarding.OnBoardingScreen
import com.example.icare.ui.presentation.registeration.signup.SignUp
import com.example.icare.ui.presentation.splash.SplashScreen
import com.example.icare.ui.theme.ICareTheme
import com.example.icare.ui.util.Destinations

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
                    Navigation()
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
fun Navigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val preferencesHelper = PreferencesHelper(context)
    NavHost(navController = navController, startDestination = start(preferencesHelper)) {
        composable(Destinations.SplashScreen.route) {
            SplashScreen(navController = navController)
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
    }
}

fun start(preferencesHelper: PreferencesHelper): String {
    return if (preferencesHelper.getBooleanValue("onBoarding")) Destinations.SignUp.route
    else
        Destinations.SplashScreen.route
}