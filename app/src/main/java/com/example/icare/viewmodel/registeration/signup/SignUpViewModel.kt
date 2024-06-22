package com.example.icare.viewmodel.registeration.signup

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.icare.model.classes.AuthError
import com.example.icare.model.classes.Destinations
import com.example.icare.model.classes.RegisterRequest
import com.example.icare.repository.AuthRepository
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

    val isUnSpecificError = mutableStateOf(false)

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
                            if (registrationError.error.contains("Email")) {
                                _registrationUiState.value = registrationUiState.value.copy(
                                    emailError = registrationError.error, isEmailError = true
                                )
                            }
                            if (registrationError.error.contains("Phone")) {
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
    }

    private fun resetState() {
        _errorMessage.value = null
        _registrationUiState.value = SignUpUiState()
    }
}
