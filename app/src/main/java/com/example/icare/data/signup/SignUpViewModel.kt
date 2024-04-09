package com.example.icare.data.signup

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.icare.core.util.Destinations
import com.example.icare.data.login.Validator
import com.google.firebase.auth.FirebaseAuth

class SignUpViewModel(private val navController: NavController) : ViewModel() {
    var registrationUIState = mutableStateOf(SignupUiState())

    var allValidationsPassed = mutableStateOf(false)

    var signUpInProgress = mutableStateOf(false)

    fun onEvent(event: SignupUIEvent) {
        when (event) {
            is SignupUIEvent.CompleteNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    completeName = event.name
                )
                printState()
            }

            is SignupUIEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
                printState()

            }

            is SignupUIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
                printState()

            }

            is SignupUIEvent.ConfirmPasswordChange -> {
                registrationUIState.value = registrationUIState.value.copy(
                    confirmPassword = event.confirmPassword
                )
                printState()
            }

            is SignupUIEvent.RegisterButtonClicked -> {
                signUp()
            }

            is SignupUIEvent.PrivacyPolicyCheckBoxClicked -> TODO()
            is SignupUIEvent.PhoneChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    phone = event.phone
                )
                printState()
            }
        }
        validateDataWithRules()
    }


    private fun signUp() {
        printState()
        createUserInFirebase(
            email = registrationUIState.value.email,
            password = registrationUIState.value.password
        )
    }

    private fun validateDataWithRules() {
        val nameResult = Validator.validateCompleteName(
            name = registrationUIState.value.completeName
        )

        val emailResult = Validator.validateEmail(
            email = registrationUIState.value.email
        )

        val phoneResult = Validator.validatePhoneNumber(
            phone = registrationUIState.value.phone
        )
        val passwordResult = Validator.validatePassword(
            password = registrationUIState.value.password
        )
        val confirmPasswordResult = Validator.validateConfirmPassword(
            confirmPassword = registrationUIState.value.confirmPassword,
            password = registrationUIState.value.password
        )

        val privacyPolicyResult = Validator.validatePrivacyPolicyAcceptance(
            statusValue = registrationUIState.value.privacyPolicyAccepted
        )


        Log.d("SignUp Bottom", "Inside_validateDataWithRules")
        Log.d("SignUp Bottom", "fNameResult= $nameResult")
        Log.d("SignUp Bottom", "emailResult= $emailResult")
        Log.d("SignUp Bottom", "passwordResult= $passwordResult")
        Log.d("SignUp Bottom", "privacyPolicyResult= $privacyPolicyResult")

        registrationUIState.value = registrationUIState.value.copy(
            completeNameError = nameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
            confirmPasswordError = confirmPasswordResult.status,
            privacyPolicyError = privacyPolicyResult.status, phoneError = phoneResult.status
        )


        allValidationsPassed.value = nameResult.status &&
                emailResult.status && passwordResult.status && phoneResult.status && confirmPasswordResult.status

    }


    private fun printState() {
        Log.d("Print State", "Inside_printState")
        Log.d("Print State", registrationUIState.value.toString())
    }


    private fun createUserInFirebase(email: String, password: String) {
        signUpInProgress.value = true
        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d("print State one", "Inside_OnCompleteListener")
                Log.d("print State one", " isSuccessful = ${it.isSuccessful}")
                signUpInProgress.value = false
                if (it.isSuccessful) {
                    navController.navigate(Destinations.MainScreen.route)
                }
            }
            .addOnFailureListener {
                Log.d("print State one", "Inside_OnFailureListener")
                Log.d("print State one", "Exception= ${it.message}")
                Log.d("print State one", "Exception= ${it.localizedMessage}")
            }
    }

}
