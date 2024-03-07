package com.example.icare.presentation.registeration.signup

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.example.icare.domain.model.enums.ErrorTypes

class SignUpViewModel : ViewModel() {
    val inputFields: MutableMap<String, MutableState<String>> = mutableMapOf(
        "Name" to mutableStateOf(""),
        "Email" to mutableStateOf(""),
        "Phone" to mutableStateOf(""),
        "Password" to mutableStateOf(""),
        "Confirm Password" to mutableStateOf("")
    )

    private val errorStates: Map<String, MutableState<ErrorTypes?>> = mapOf(
        "Name" to mutableStateOf(null),
        "Email" to mutableStateOf(null),
        "Phone" to mutableStateOf(null),
        "Password" to mutableStateOf(null),
        "Confirm Password" to mutableStateOf(null)

    )


    fun handleContinueClick() {
        resetErrorStates()
        inputFields.forEach { (label, valueState) ->
            val value = valueState.value
            when (label) {
                "Name", "Email", "Phone", "Password", "Confirm Password" -> {
                    if (value.isEmpty()) {
                        errorStates[label]?.value = ErrorTypes.EMPTY
                    } else {
                        when (label) {
                            "Email" -> {
                                if (!validateEmail(value)) {
                                    errorStates[label]?.value = ErrorTypes.INVALID_EMAIL
                                }
                            }

                            "Phone" -> {
                                if (!validatePhone(value)) {
                                    errorStates[label]?.value = ErrorTypes.INVALID_PHONE_NUMBER
                                }
                            }

                            "Password" -> {
                                if (!validatePassword(value)) {
                                    errorStates[label]?.value = ErrorTypes.SHORT_PASSWORD
                                }
                            }

                            "Confirm Password" -> {
                                if (value != inputFields["Password"]?.value) {
                                    errorStates[label]?.value = ErrorTypes.PASSWORD_MISMATCH
                                }
                            }
                        }
                    }
                }

            }

        }
    }

    fun errorMessage(label: String, context: Context): String? {
        val errorType = errorStates[label]?.value
        return errorType?.let { context.getString(it.errorMessage) }
    }

    fun isError(label: String): Boolean = errorStates[label]?.value != null

    private fun validateEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun validatePhone(phone: String): Boolean = Patterns.PHONE.matcher(phone).matches()

    private fun validatePassword(password: String): Boolean = password.length >= 8

    private fun resetErrorStates() {
        errorStates.forEach { (_, errorState) ->
            errorState.value = null
        }
    }
}
