package com.example.icare.presentation.mainscreen.screens.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.icare.core.util.Destinations

class ProfileViewModel(private val navController: NavController) : ViewModel() {
    var isDialog = mutableStateOf(false)
    val userInfo = UserProfile(name = "Hisham Mohamed", email = "Hishamohmad19@gmail.com")

    fun handleClickAction(action: String) {
        when (action) {
            "Edit Profile" -> {
                navController.navigate(Destinations.EditProfile.route)
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