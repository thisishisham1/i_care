package com.example.icare.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.icare.R
import com.example.icare.data.PreferencesHelper
import com.example.icare.core.theme.green500

@Composable
fun SplashScreen(navController: NavController, preferencesHelper: PreferencesHelper) {
    val splashViewModel = remember {
        SplashViewModel(preferencesHelper = preferencesHelper)
    }
    LaunchedEffect(key1 = true) {
        splashViewModel.animateScale(splashViewModel.scale)
        splashViewModel.delayAndNavigate(navController)
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(green500)
    ) {
        AsyncImage(
            model = R.drawable.logo,
            contentDescription = "",
            Modifier
                .requiredSize(250.dp)
                .scale(splashViewModel.scale.value)
        )
    }
}