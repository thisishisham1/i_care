package com.example.icare.presentation.mainscreen.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {
    val valueState = mutableStateOf("")
    fun onValueChange(newValue: String) {
        valueState.value = newValue
    }
}