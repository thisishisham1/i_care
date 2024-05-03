package com.example.icare.viewmodel.registeration.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.icare.model.classes.Destinations
import com.example.icare.view.registeration.login.LoginUIEvent
import com.example.icare.view.registeration.login.LoginUIState
import com.example.icare.view.registeration.login.LoginValidator


class LoginViewModel(val navController: NavController) : ViewModel() {
    var loginUIState = mutableStateOf(LoginUIState())

    private var allValidationsPassed = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)

    fun onEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    email = event.email
                )
            }

            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )
            }

            is LoginUIEvent.LoginButtonClicked -> {
                validateLoginUIDataWithRules()
                if (allValidationsPassed.value) {
                    loginClick()
                }
            }
        }

    }

    private fun validateLoginUIDataWithRules() {
        val emailResult = LoginValidator.validateEmail(
            email = loginUIState.value.email
        )
        val passwordResult = LoginValidator.validatePassword(
            password = loginUIState.value.password
        )

        loginUIState.value = loginUIState.value.copy(
            emailError = !emailResult.status,
            passwordError = !passwordResult.status,
        )

        allValidationsPassed.value = emailResult.status && passwordResult.status
    }

    private fun loginClick() {

    }


    fun handleForgotPasswordButton() {
        navController.navigate(Destinations.Auth.ForgotPassword.route)
    }

    fun handleSignUpButton() {
        navController.navigate(Destinations.Auth.SignUp.route)
    }

}
