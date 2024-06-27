package com.example.icare.view.main.bottomnavitems.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.icare.core.reusablecomponent.DefaultTopAppBar

@Composable
fun NotificationsView(navController: NavController) {
    Scaffold(
        topBar = {
            DefaultTopAppBar(title = "Notification", navController = navController)
        }
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
        }
    }
}