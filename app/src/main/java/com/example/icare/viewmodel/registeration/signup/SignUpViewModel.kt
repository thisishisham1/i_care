package com.example.icare.viewmodel.registeration.signup

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.icare.model.classes.Destinations
import com.example.icare.model.classes.RegisterRequest
import com.example.icare.model.classes.UserResponse
import com.example.icare.repository.AuthRepository
import com.example.icare.view.registeration.login.SignUpValidator
import com.example.icare.view.registeration.signup.SignUpUiState
import com.example.icare.view.registeration.signup.SignupUIEvent
import com.google.gson.Gson
import kotlinx.coroutines.launch

class SignUpViewModel(private val navController: NavController) : ViewModel() {
    var registrationUiState = mutableStateOf(SignUpUiState())

    private var isRegistrationDataValid = mutableStateOf(false)

    var isRegistrationInProgress = mutableStateOf(false)

    var errorMessage = mutableStateOf<String?>(null)


    fun onEvent(event: SignupUIEvent) {
        when (event) {
            is SignupUIEvent.CompleteNameChanged -> {
                registrationUiState.value = registrationUiState.value.copy(
                    completeName = event.name
                )
            }

            is SignupUIEvent.EmailChanged -> {
                registrationUiState.value = registrationUiState.value.copy(
                    email = event.email
                )

            }

            is SignupUIEvent.PasswordChanged -> {
                registrationUiState.value = registrationUiState.value.copy(
                    password = event.password
                )

            }

            is SignupUIEvent.ConfirmPasswordChange -> {
                registrationUiState.value = registrationUiState.value.copy(
                    confirmPassword = event.confirmPassword
                )
            }

            is SignupUIEvent.RegisterButtonClicked -> {
                validateDataWithRules()
                if (isRegistrationDataValid.value) {
                    handleRegisterButtonClick()
                }
            }

            is SignupUIEvent.PrivacyPolicyCheckBoxClicked -> {
                registrationUiState.value = registrationUiState.value.copy(
                    privacyPolicyAccepted = event.status
                )
            }

            is SignupUIEvent.PhoneChanged -> {
                registrationUiState.value = registrationUiState.value.copy(
                    phone = event.phone
                )
            }
        }
    }


    private fun handleRegisterButtonClick() {
        viewModelScope.launch {
            try {
                isRegistrationInProgress.value = true
                val response = AuthRepository().registerUser(
                    RegisterRequest(
                        email = registrationUiState.value.email,
                        name1 = registrationUiState.value.completeName,
                        phone = registrationUiState.value.phone,
                        pass = registrationUiState.value.password
                    )
                )
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
                e.printStackTrace()
                errorMessage.value =
                    "An error occurred during login. Please check your network connection and try again."

            } finally {
                isRegistrationInProgress.value = false
            }
        }
    }

    private fun validateDataWithRules() {
        val nameResult = SignUpValidator.validateCompleteName(
            name = registrationUiState.value.completeName
        )

        val emailResult = SignUpValidator.validateEmail(
            email = registrationUiState.value.email
        )

        val phoneResult = SignUpValidator.validatePhoneNumber(
            phone = registrationUiState.value.phone
        )
        val passwordResult = SignUpValidator.validatePassword(
            password = registrationUiState.value.password
        )
        val confirmPasswordResult = SignUpValidator.validateConfirmPassword(
            confirmPassword = registrationUiState.value.confirmPassword,
            password = registrationUiState.value.password
        )
        val privacyPolicyResult = SignUpValidator.validatePrivacyPolicyAcceptance(
            statusValue = registrationUiState.value.privacyPolicyAccepted
        )

        registrationUiState.value = registrationUiState.value.copy(
            completeNameError = !nameResult.status,
            emailError = !emailResult.status,
            passwordError = !passwordResult.status,
            confirmPasswordError = !confirmPasswordResult.status,
            privacyPolicyError = !privacyPolicyResult.status,
            phoneError = !phoneResult.status
        )


        isRegistrationDataValid.value =
            nameResult.status && emailResult.status && passwordResult.status && phoneResult.status && confirmPasswordResult.status && privacyPolicyResult.status

    }


}
