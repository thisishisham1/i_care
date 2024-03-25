package com.example.icare.presentation.mainscreen.screens.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class ProfileViewModel(private val navController: NavController) : ViewModel() {
    var isDialog = mutableStateOf(false)

    fun handleClickAction(action: String) {
        when (action) {
            "Edit Profile" -> {
                // todo: Handle edit profile action
            }

            "Settings" -> {
                // todo: Handle settings action
            }

            "Terms and Conditions" -> {
                // todo: Handle terms and conditions action
            }

            "Log Out" -> {
                // todo: Handle log out action
                isDialog.value = true
            }
        }
    }

    fun handleLogoutDialog() {
        isDialog.value = true
    }

    fun handleCancelDialog() {
        isDialog.value = false // Set the dialog state to false to dismiss the dialog
    }
}