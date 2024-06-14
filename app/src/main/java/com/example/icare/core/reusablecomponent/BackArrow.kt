package com.example.icare.core.reusablecomponent

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun BackArrow(navController: NavController) {
    IconButton(onClick = { navController.navigateUp() }) {
        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Arrow Back")
    }
}