package com.example.icare.viewmodel.main.bottomnavitems.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.icare.model.classes.Destinations
import com.example.icare.model.sharedPreferences.PreferencesHelper
import com.example.icare.repository.AuthRepository
import com.example.icare.view.main.bottomnavitems.profile.UserProfile
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val navController: NavController, private val preferencesHelper: PreferencesHelper
) : ViewModel() {
    var isDialog = mutableStateOf(false)
    val userInfo = UserProfile(name = "Hisham Mohamed", email = "Hishamohmad19@gmail.com")

    val authRepository = AuthRepository()

    fun handleClickAction(action: String) {
        when (action) {
            "Edit Profile" -> {
                navController.navigate(Destinations.Profile.EditProfile.route)
            }

            "Settings" -> {
                // todo: Handle settings action
            }

            "Terms and Conditions" -> {
                navController.navigate(Destinations.Main.TermsAndConditions.route)
            }

            "Log Out" -> {
                // todo: Handle log out action
                isDialog.value = true
            }
        }
    }

    fun handleLogoutDialog() {
        viewModelScope.launch {
            authRepository.logout()
            navController.navigate(Destinations.Auth.Login.route) {
                popUpTo(0)
            }
            isDialog.value = false
        }


    }

    fun handleCancelDialog() {
        isDialog.value = false // Set the dialog state to false to dismiss the dialog
    }
}