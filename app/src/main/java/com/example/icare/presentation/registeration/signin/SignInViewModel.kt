package com.example.icare.presentation.registeration.signin

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.icare.domain.model.enums.ErrorInfo
import com.example.icare.domain.model.enums.ErrorTypes


class SignInViewModel(val navController: NavController) : ViewModel() {
    val inputFields: MutableMap<String, MutableState<String>> = mutableMapOf(
        "Email" to mutableStateOf(""),
        "Password" to mutableStateOf("")
    )
    private val errorStates: MutableMap<String, MutableState<ErrorTypes?>> = mutableMapOf(
        "Email" to mutableStateOf(null),
        "Password" to mutableStateOf(null)
    )

    fun handleSignInButton() {
        resetErrorState()
        inputFields.forEach { (label, state) ->
            val stateValue = state.value
            when (label) {
                "Email", "Password" -> {
                    if (stateValue.isEmpty()) {
                        errorStates[label]?.value = ErrorTypes.EMPTY
                    } else {
                        when (label) {
                            "Email" -> {
                                if (!isValidEmail(email = stateValue)) {
                                    errorStates[label]?.value = ErrorTypes.EMAIL_NOT_FOUNDED
                                }
                            }

                            "Password" -> {
                                if (!isValidPassword(password = stateValue)) {
                                    errorStates[label]?.value = ErrorTypes.INCORRECT_PASSWORD
                                }
                            }

                        }
                    }
                }

            }
        }
    }

    private fun resetErrorState() {
        errorStates.values.forEach { it.value = null }
    }


    fun isError(label: String) = errorStates[label]?.value != null
    fun errorMessage(label: String, context: Context): String? {
        val errorType = errorStates[label]?.value
        return errorType?.let { context.getString(it.errorMessage) }
    }

    private fun isValidEmail(email: String) = email == "Hishamohmad19@gmail.com"

    private fun isValidPassword(password: String) = password == "CALLMEH19"

}