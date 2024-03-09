package com.example.icare.presentation.registeration.forgotpassword

import android.content.Context
import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.icare.core.util.Destinations
import com.example.icare.domain.model.enums.ErrorTypes

class ResetPasswordViewModel(val navController: NavController) : ViewModel() {
    // Mutable state for email and error handling
    val emailValue = mutableStateOf("")
    private val errorEmailState = mutableStateOf<ErrorTypes?>(null)

    // Mutable state for password and error handling
    private val password = mutableStateOf("")
    private val confirmPassword = mutableStateOf("")
    private val errorPasswordState: MutableMap<String, MutableState<ErrorTypes?>> = mutableMapOf()

    // List of password fields for UI
    val passwordFields = mapOf("Password" to password, "Confirm Password" to confirmPassword)

    init {
        // Initialize error states for password fields
        passwordFields.keys.forEach { label ->
            errorPasswordState[label] = mutableStateOf(null)
        }
    }


    // Function to handle submit button click
    fun handleSubmitButton() {
        resetErrorState()
        validateEmail()
    }

    // Function to handle change password button click
    fun handleChangePasswordButton() {
        resetPasswordErrorState()
        validatePasswordFields()
    }

    // Function to handle email validation
    private fun validateEmail() {
        when {
            emailValue.value.isEmpty() -> setError(ErrorTypes.EMPTY)
            !validateEmailFormat() -> setError(ErrorTypes.INVALID_EMAIL_FORMAT)
            emailValue.value == "Hishamohmad19@gmail.com" -> navController.navigate(Destinations.ResetPassword.route)
            else -> setError(ErrorTypes.EMAIL_NOT_FOUNDED)
        }
    }

    // Function to handle password fields validation
    private fun validatePasswordFields() {
        passwordFields.forEach { (label, value) ->

            when (label) {
                "Password" -> {
                    if (value.value.isEmpty()) {
                        setPasswordError(ErrorTypes.EMPTY, label)
                    } else if (!validatePasswordLength(value.value)) {
                        setPasswordError(ErrorTypes.SHORT_PASSWORD, label)
                    }
                }

                "Confirm Password" -> {
                    if (value.value != passwordFields["Password"]?.value) {
                        setPasswordError(ErrorTypes.PASSWORD_MISMATCH, label)
                    }
                }

            }
        }
    }

    // Function to validate email format
    private fun validateEmailFormat(): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(emailValue.value).matches()

    // Function to validate password length
    private fun validatePasswordLength(password: String): Boolean = password.length >= 8

    // Function to set error state for email
    private fun setError(errorType: ErrorTypes) {
        errorEmailState.value = errorType
    }

    // Function to set error state for password fields
    private fun setPasswordError(errorType: ErrorTypes, label: String) {
        errorPasswordState[label]?.value = errorType
    }

    // Function to reset error state for email
    private fun resetErrorState() {
        errorEmailState.value = null
    }

    // Function to reset error state for password fields
    private fun resetPasswordErrorState() {
        errorPasswordState.values.forEach { it.value = null }
    }

    // Function to check if there is any error for a specific input field
    fun isError(label: String): Boolean = errorPasswordState[label]?.value != null

    // Function to get error message for Password
    fun getErrorMessage(context: Context, label: String): String? {
        return errorPasswordState[label]?.value?.let { context.getString(it.errorMessage) }
    }

    // Function to check if there is any error for email
    fun isError(): Boolean = errorEmailState.value != null

    // Function to get error message for email
    fun getErrorMessage(context: Context): String? =
        errorEmailState.value?.errorMessage?.let { context.getString(it) }
}
