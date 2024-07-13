package com.example.icare.viewmodel.main.bottomnavitems.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.icare.MyApplication
import com.example.icare.model.classes.Destinations
import com.example.icare.model.classes.apiClass.UserResponse
import com.example.icare.model.local.UserDatabase
import com.example.icare.model.sharedPreferences.PreferencesHelper
import com.example.icare.repository.AuthRepository
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val navController: NavController, private val preferencesHelper: PreferencesHelper
) : ViewModel() {
    var isDialog = mutableStateOf(false)

    private val authRepository = AuthRepository()
    val userDao = UserDatabase.getDatabase(MyApplication.applicationContext()).userResponseDao()
    val user: LiveData<UserResponse> = liveData {
        emit(userDao.getUser())
    }

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