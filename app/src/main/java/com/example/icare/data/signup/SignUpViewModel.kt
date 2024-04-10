package com.example.icare.data.signup

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.icare.core.util.Destinations
import com.example.icare.data.login.SignUpValidator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException

class SignUpViewModel(private val navController: NavController) : ViewModel() {
    var registrationUiState = mutableStateOf(SignupUiState())

    var isRegistrationDataValid = mutableStateOf(false)

    var isRegistrationInProgress = mutableStateOf(false)

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
                    initiateRegistration()
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
        validateDataWithRules()
        if (isRegistrationDataValid.value) {
            initiateRegistration()
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


        isRegistrationDataValid.value = nameResult.status &&
                emailResult.status &&
                passwordResult.status &&
                phoneResult.status &&
                confirmPasswordResult.status &&
                privacyPolicyResult.status

    }


    private fun initiateRegistration() {
        isRegistrationInProgress.value = true
        val email = registrationUiState.value.email
        val password = registrationUiState.value.password
        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                isRegistrationInProgress.value = false
                if (it.isSuccessful) {
                    navController.navigate(Destinations.MainScreen.route)
                }
            }
            .addOnFailureListener {
                isRegistrationInProgress.value = false
                if (it is FirebaseAuthUserCollisionException) {
                    registrationUiState.value =
                        registrationUiState.value.copy(
                            errorMessageForEmail = "Email already exists",
                            emailError = true
                        )
                }
            }
    }

}
