package com.example.icare.viewmodel.registeration.signup

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.icare.MainViewModel
import com.example.icare.model.classes.Destinations
import com.example.icare.model.classes.apiClass.AuthError
import com.example.icare.model.classes.apiClass.RegisterRequest
import com.example.icare.repository.AuthRepository
import com.example.icare.repository.UsersRepository
import com.example.icare.view.registeration.signup.SignUpUiState
import com.example.icare.view.registeration.signup.SignupUIEvent
import kotlinx.coroutines.launch

class SignUpViewModel(private val navController: NavController) : ViewModel() {
    private val _registrationUiState = mutableStateOf(SignUpUiState())
    val registrationUiState: State<SignUpUiState> get() = _registrationUiState

    private val _isRegistrationInProgress = mutableStateOf(false)
    val isRegistrationInProgress: State<Boolean> get() = _isRegistrationInProgress

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> get() = _errorMessage


    fun onEvent(event: SignupUIEvent) {
        when (event) {
            is SignupUIEvent.CompleteNameChanged -> {
                _registrationUiState.value = registrationUiState.value.copy(
                    completeName = event.name
                )
            }

            is SignupUIEvent.EmailChanged -> {
                _registrationUiState.value = registrationUiState.value.copy(
                    email = event.email
                )

            }

            is SignupUIEvent.PasswordChanged -> {
                _registrationUiState.value = registrationUiState.value.copy(
                    password = event.password
                )

            }

            is SignupUIEvent.RegisterButtonClicked -> {
                handleRegisterButtonClick()
            }

            is SignupUIEvent.PrivacyPolicyCheckBoxClicked -> {
                _registrationUiState.value = registrationUiState.value.copy(
                    privacyPolicyAccepted = event.status
                )
            }

            is SignupUIEvent.PhoneChanged -> {
                _registrationUiState.value = registrationUiState.value.copy(
                    phone = event.phone
                )
            }

            is SignupUIEvent.LoginButtonClick -> {
                handleLoginClick()
            }
        }
    }

    private fun handleLoginClick() {
        navController.navigate(Destinations.Auth.Login.route) {
            popUpTo(0)
        }
    }

    private fun handleRegisterButtonClick() {
        viewModelScope.launch {
            try {
                _isRegistrationInProgress.value = true
                val response = AuthRepository().registerUser(
                    RegisterRequest(
                        email = registrationUiState.value.email,
                        name1 = registrationUiState.value.completeName,
                        phone = registrationUiState.value.phone,
                        pass = registrationUiState.value.password
                    )
                )
                if (response.isSuccess) {
                    navController.navigate(Destinations.Main.MainScreen.route) {
                        popUpTo(0)
                        val userRepo = UsersRepository()
                        MainViewModel(userRepo).fetchReservation()
                    }
                } else {
                    val registrationError = response.exceptionOrNull() as? AuthError
                    _errorMessage.value = registrationError?.message
                    when (registrationError) {
                        is AuthError.BadRequest -> {
                            if (registrationError.error.contains("Name")) {
                                _registrationUiState.value = registrationUiState.value.copy(
                                    nameError = registrationError.error, isNameError = true
                                )
                            }
                            if (registrationError.error.contains("Email", ignoreCase = true)) {
                                _registrationUiState.value = registrationUiState.value.copy(
                                    emailError = registrationError.error, isEmailError = true
                                )
                            }
                            if (registrationError.error.contains("Phone", ignoreCase = true)) {
                                _registrationUiState.value = registrationUiState.value.copy(
                                    phoneError = registrationError.error, isPhoneError = true
                                )
                            }
                            if (registrationError.error.contains("Password")) {
                                _registrationUiState.value = registrationUiState.value.copy(
                                    passwordError = registrationError.error, isPasswordError = true
                                )
                            }
                        }

                        is AuthError.Unauthorized -> {
                            _errorMessage.value = registrationError.message
                        }

                        is AuthError.Forbidden -> {
                            _errorMessage.value = registrationError.message
                        }

                        is AuthError.NotFound -> {
                            _errorMessage.value = registrationError.message
                        }

                        is AuthError.InternalServerError -> {
                            _errorMessage.value = registrationError.message
                        }

                        is AuthError.Unknown -> {
                            _errorMessage.value = registrationError.message
                        }

                        else -> {
                            _errorMessage.value = registrationError?.message
                        }

                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessage.value =
                    "An error occurred during login. Please check your network connection and try again."

            } finally {
                _isRegistrationInProgress.value = false
            }
        }
        _registrationUiState.value = _registrationUiState.value.copy(
            isEmailError = false,
            isPasswordError = false,
            emailError = null,
            passwordError = null,
            nameError = null,
            isNameError = false,
            isPhoneError = false,
            phoneError = null
        )
    }
}
