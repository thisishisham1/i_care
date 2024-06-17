package com.example.icare.viewmodel.registeration.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.icare.model.classes.Destinations
import com.example.icare.model.classes.UserResponse
import com.example.icare.repository.AuthRepository
import com.example.icare.view.registeration.login.LoginUIEvent
import com.example.icare.view.registeration.login.LoginUIState
import com.example.icare.view.registeration.login.LoginValidator
import com.google.gson.Gson
import kotlinx.coroutines.launch


class LoginViewModel(val navController: NavController) : ViewModel() {
    var loginUIState = mutableStateOf(LoginUIState())
    var errorMessage = mutableStateOf<String?>(null)
    private var allValidationsPassed = mutableStateOf(false)
    var isChecking = mutableStateOf(false)
    var loginInProgress = mutableStateOf(false)

    private val authRepository = AuthRepository()
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
        viewModelScope.launch {
            try {
                Log.d("LoginClick", "Login started")
                isChecking.value = true
                Log.d("LoginClick", "After setting isChecking")
                val response =
                    authRepository.loginUser(loginUIState.value.email, loginUIState.value.password)
                if (response.status) {
                    navController.navigate(Destinations.Main.MainScreen.route) {
                        popUpTo(0)
                    }
                } else {
                    errorMessage.value = response.message
                }
            } catch (e: retrofit2.HttpException) {
                when (e.code()) {
                    400 -> {
                        val errorJsonString = e.response()?.errorBody()?.string()
                        val errorResponse =
                            Gson().fromJson(errorJsonString, UserResponse::class.java)
                        errorMessage.value = errorResponse.message
                    }

                    401 -> errorMessage.value = "Unauthorized: Please check your credentials."
                    403 -> errorMessage.value =
                        "Forbidden: You don't have permission to access the resource."

                    404 -> errorMessage.value =
                        "Not Found: The resource you are looking for could not be found."

                    500 -> errorMessage.value =
                        "Internal Server Error: Something went wrong on the server."

                    else -> errorMessage.value = "An unknown error occurred."
                }
            } catch (e: Exception) {
                Log.e("LoginClick", "Error during login", e) // Add this line
                e.printStackTrace()
                errorMessage.value =
                    "An error occurred during login. Please check your network connection and try again."
            } finally {
                isChecking.value = false
            }
        }
    }


    fun handleForgotPasswordButton() {
        navController.navigate(Destinations.Auth.ForgotPassword.route)
    }

    fun handleSignUpButton() {
        navController.navigate(Destinations.Auth.SignUp.route)
    }
}
