package com.example.icare.presentation.mainscreen.screens.profile.edit_profile

import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class EditProfileViewModel(val navController: NavController) : ViewModel() {
    val isError = mutableStateOf(false)
    val name = mutableStateOf("Hisham Mohamed")
    val email = mutableStateOf("Hishamohmad19@gmail.com")
    fun handleBackArrow() = navController.navigateUp()

    fun onEmailValueChange(value: String) {
        email.value = value
        isError.value = !isValidEmail(value)
    }

    private fun isValidEmail(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()

}