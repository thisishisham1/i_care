package com.example.icare.viewmodel.registeration.login

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.icare.model.classes.AuthError
import com.example.icare.model.classes.Destinations
import com.example.icare.model.classes.LoginRequest
import com.example.icare.model.sharedPreferences.PreferencesHelper
import com.example.icare.repository.AuthRepository
import com.example.icare.view.registeration.login.LoginUIEvent
import com.example.icare.view.registeration.login.LoginUIState
import kotlinx.coroutines.launch


class LoginViewModel(
    val navController: NavController,
    private val preferencesHelper: PreferencesHelper
) : ViewModel() {
    private val _loginUiState = mutableStateOf(LoginUIState())
    val loginUiState: State<LoginUIState> get() = _loginUiState

    private val _loginInProgress = mutableStateOf(false)
    val isLoginInProgress: State<Boolean> get() = _loginInProgress

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> get() = _errorMessage

    private val authRepository = AuthRepository()

    fun onEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.EmailChanged -> {
                _loginUiState.value = loginUiState.value.copy(
                    email = event.email
                )
            }

            is LoginUIEvent.PasswordChanged -> {
                _loginUiState.value = loginUiState.value.copy(
                    password = event.password
                )
            }

            is LoginUIEvent.LoginButtonClicked -> {
                loginClick()
            }
        }

    }

    private fun loginClick() {
        viewModelScope.launch {
            try {
                _loginInProgress.value = true
                val response = authRepository.loginUser(
                    LoginRequest(
                        email = _loginUiState.value.email, pass = _loginUiState.value.password
                    )
                )
                if (response.isSuccess) {
                    val userResponse = response.getOrNull()
                    val userToken = userResponse?.patient_token
                    preferencesHelper.saveUserLogin(userToken!!)
                    navController.navigate(Destinations.Main.MainScreen.route) {
                        popUpTo(0)
                    }
                } else {
                    val loginError = response.exceptionOrNull() as? AuthError
                    _errorMessage.value = loginError?.message
                    when (loginError) {
                        is AuthError.BadRequest -> {
                            if (loginError.error.contains("Email", ignoreCase = true)) {
                                _loginUiState.value = loginUiState.value.copy(
                                    emailError = loginError.error, isEmailError = true
                                )
                            }
                            if (loginError.error.contains("Password", ignoreCase = true)) {
                                _loginUiState.value = loginUiState.value.copy(
                                    passwordError = loginError.error, isPasswordError = true
                                )
                            }
                            if (loginError.error.contains(
                                    "Incorrect email or password.", ignoreCase = true
                                )
                            ) {
                                _errorMessage.value = loginError.error
                            }
                        }

                        is AuthError.Unauthorized -> {
                            _errorMessage.value = loginError.message
                        }

                        is AuthError.Forbidden -> {
                            _errorMessage.value = loginError.message
                        }

                        is AuthError.NotFound -> {
                            _errorMessage.value = loginError.message
                        }

                        is AuthError.InternalServerError -> {
                            _errorMessage.value = loginError.message
                        }

                        is AuthError.Unknown -> {
                            _errorMessage.value = loginError.message
                        }

                        else -> {
                            _errorMessage.value = loginError?.message
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("LoginClick", "Error during login", e)
                e.printStackTrace()
                _errorMessage.value =
                    "An error occurred during login. Please check your network connection and try again."
            } catch (t: Throwable) {
                Log.e("LoginClick", "Unexpected error occurred ", t)
            } finally {
                _loginInProgress.value = false
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

