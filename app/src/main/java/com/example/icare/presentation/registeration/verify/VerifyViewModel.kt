package com.example.icare.presentation.registeration.verify

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.icare.core.util.Destinations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class VerifyViewModel(val navController: NavController) : ViewModel() {
    val textFieldStates = (1..4).map { mutableStateOf("") }.toMutableList()
    var isError = mutableStateOf(false)
    fun handleVerifyButton() {
        val entreCode = textFieldStates.joinToString("") { it.value }
        val isCorrect = checkCode(entreCode)
        if (isCorrect) {
            navController.navigate(Destinations.DoneVerify.route) {
                popUpTo(0)
            }
        } else {
            isError.value = true
        }
    }

    private fun checkCode(code: String): Boolean {
        return code.equals("1234", ignoreCase = true)
    }
}