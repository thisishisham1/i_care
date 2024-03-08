package com.example.icare.presentation.registeration.signin

import android.content.Context
import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.icare.domain.model.enums.ErrorTypes


class SignInViewModel(val navController: NavController) : ViewModel() {
    // Mutable map to hold input fields and their corresponding states
    val inputFields: MutableMap<String, MutableState<String>> = mutableMapOf(
        "Email" to mutableStateOf(""),
        "Password" to mutableStateOf("")
    )

    // Mutable map to hold error states for each input field
    private val errorStates: MutableMap<String, MutableState<ErrorTypes?>> = mutableMapOf()

    init {
        // Initialize error states for each input field
        inputFields.keys.forEach { label ->
            errorStates[label] = mutableStateOf(null)
        }
    }

    // Function to handle sign-in button click
    fun handleSignInButton() {
        resetErrorState()
        inputFields.forEach { (label, state) ->
            val stateValue = state.value
            when (label) {
                "Email" -> {
                    if (stateValue.isEmpty()) {
                        setError(ErrorTypes.EMPTY, label)
                    } else if (!isValidEmail(stateValue)) {
                        setError(ErrorTypes.EMAIL_NOT_FOUNDED, label)
                    }
                }
                "Password" -> {
                    if (stateValue.isEmpty()) {
                        setError(ErrorTypes.EMPTY, label)
                    } else if (!isValidPassword(stateValue)) {
                        setError(ErrorTypes.INCORRECT_PASSWORD, label)
                    }
                }
            }
        }
    }

    // Function to reset error states for all input fields
    private fun resetErrorState() {
        errorStates.values.forEach { it.value = null }
    }

    // Function to check if there is an error for a specific input field
    fun isError(label: String): Boolean = errorStates[label]?.value != null

    // Function to get error message for a specific input field
    fun errorMessage(label: String, context: Context): String? {
        val errorType = errorStates[label]?.value
        return errorType?.let { context.getString(it.errorMessage) }
    }

    // Function to set error state for a specific input field
    private fun setError(errorType: ErrorTypes, label: String) {
        errorStates[label]?.value = errorType
    }

    // Function to validate email format (stub implementation)
    private fun isValidEmail(email: String): Boolean = email == "Hishamohmad19@gmail.com"

    // Function to validate password (stub implementation)
    private fun isValidPassword(password: String): Boolean = password == "CALLMEH19"
}
