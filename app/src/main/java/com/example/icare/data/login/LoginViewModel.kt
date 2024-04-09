package com.example.icare.data.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.icare.core.util.Destinations
import com.google.firebase.auth.FirebaseAuth


class LoginViewModel(val navController: NavController) : ViewModel() {
    var loginUIState = mutableStateOf(LoginUIState())

    var allValidationsPassed = mutableStateOf(false)

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
                loginClick()
            }
        }
        validateLoginUIDataWithRules()
    }

    private fun validateLoginUIDataWithRules() {
        val emailResult = Validator.validateEmail(
            email = loginUIState.value.email
        )
        val passwordResult = Validator.validatePassword(
            password = loginUIState.value.password
        )

        loginUIState.value = loginUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )

        allValidationsPassed.value = emailResult.status && passwordResult.status
    }

    private fun loginClick() {
        loginInProgress.value = true
        val email = loginUIState.value.email
        val password = loginUIState.value.password

        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d("Login in button", it.isSuccessful.toString())
                if (it.isSuccessful) {
                    loginInProgress.value = false
                    navController.navigate(Destinations.MainScreen.route)
                }
            }.addOnFailureListener {
                it.localizedMessage?.let { it1 ->
                    Log.d("Login in button", it1)
                    loginInProgress.value = false
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
