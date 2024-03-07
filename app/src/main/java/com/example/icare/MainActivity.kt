package com.example.icare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.icare.data.PreferencesHelper
import com.example.icare.core.theme.ICareTheme

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
                    MainNavigation(context = this)
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