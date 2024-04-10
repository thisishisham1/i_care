package com.example.icare.data.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.icare.core.util.Destinations
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException


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
        loginInProgress.value = true
        val email = loginUIState.value.email
        val password = loginUIState.value.password
        loginUIState.value.emailError = false
        loginUIState.value.passwordError = false
        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnFailureListener {
                loginInProgress.value = false
                val genericError = "Invalid email or password."
                when (it) {
                    is FirebaseAuthInvalidCredentialsException -> {
                        loginUIState.value = loginUIState.value.copy(
                            passwordError = true,
                            genericError = genericError
                        )
                    }
                    is FirebaseNetworkException -> {
                        loginUIState.value = loginUIState.value.copy(
                            genericError = "Network error. Please check your connection and try again."
                        )
                    }
                    // Handle other specific error cases as needed
                    else -> {
                        loginUIState.value = loginUIState.value.copy(
                            genericError = genericError
                        )
                        Log.w("Login", "Error during login: ${it.message}")
                    }
                }
            }
    }


    fun handleForgotPasswordButton() {
        navController.navigate(Destinations.ForgotPassword.route)
    }

    fun handleSignUpButton() {
        navController.navigate(Destinations.SignUp.route)
    }

}
