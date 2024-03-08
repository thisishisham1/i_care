package com.example.icare.presentation.registeration.signup

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.example.icare.domain.model.enums.ErrorTypes

class SignUpViewModel : ViewModel() {
    // Mutable map to hold input fields and their corresponding states
    val inputFields: MutableMap<String, MutableState<String>> = mutableMapOf(
        "Name" to mutableStateOf(""),
        "Email" to mutableStateOf(""),
        "Phone" to mutableStateOf(""),
        "Password" to mutableStateOf(""),
        "Confirm Password" to mutableStateOf("")
    )

    // Immutable map to hold error states for each input field
    private val errorStates: Map<String, MutableState<ErrorTypes?>> = mapOf(
        "Name" to mutableStateOf(null),
        "Email" to mutableStateOf(null),
        "Phone" to mutableStateOf(null),
        "Password" to mutableStateOf(null),
        "Confirm Password" to mutableStateOf(null)
    )

    // Function to handle continue button click
    fun handleContinueClick() {
        resetErrorStates()
        inputFields.forEach { (label, valueState) ->
            val value = valueState.value
            when (label) {
                "Name", "Email", "Phone", "Password", "Confirm Password" -> {
                    if (value.isEmpty()) {
                        setError(ErrorTypes.EMPTY, label)
                    } else {
                        when (label) {
                            "Email" -> {
                                if (!validateEmail(value)) {
                                    setError(ErrorTypes.INVALID_EMAIL, label)
                                }
                            }

                            "Phone" -> {
                                if (!validatePhone(value)) {
                                    setError(ErrorTypes.INVALID_PHONE_NUMBER, label)
                                }
                            }

                            "Password" -> {
                                if (!validatePassword(value)) {
                                    setError(ErrorTypes.SHORT_PASSWORD, label)
                                }
                            }

                            "Confirm Password" -> {
                                if (value != inputFields["Password"]?.value) {
                                    setError(ErrorTypes.PASSWORD_MISMATCH, label)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // Function to get error message for a specific input field
    fun errorMessage(label: String, context: Context): String? {
        val errorType = errorStates[label]?.value
        return errorType?.let { context.getString(it.errorMessage) }
    }

    // Function to check if there is an error for a specific input field
    fun isError(label: String): Boolean = errorStates[label]?.value != null

    // Function to validate email format
    private fun validateEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    // Function to validate phone number format
    private fun validatePhone(phone: String): Boolean =
        Patterns.PHONE.matcher(phone).matches()

    // Function to validate password length
    private fun validatePassword(password: String): Boolean = password.length >= 8

    // Function to set error state for a specific input field
    private fun setError(errorType: ErrorTypes, label: String) {
        errorStates[label]?.value = errorType
    }

    // Function to reset error states for all input fields
    private fun resetErrorStates() {
        errorStates.values.forEach { it.value = null }
    }
}
