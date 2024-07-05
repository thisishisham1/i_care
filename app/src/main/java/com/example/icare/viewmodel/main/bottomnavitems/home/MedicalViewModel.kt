package com.example.icare.viewmodel.main.bottomnavitems.home

import androidx.lifecycle.ViewModel
import com.example.icare.core.reusablecomponent.MedicalMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MedicalViewModel : ViewModel() {
    private val _messages = MutableStateFlow<List<MedicalMessage>>(emptyList())
    val messages: StateFlow<List<MedicalMessage>> = _messages.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        _messages.value = listOf(
            MedicalMessage("Hi, I'm ready to assist you with medical image analysis.", "Bot"),

            MedicalMessage(
                "Please upload an image for analysis.", "Bot"
            ),
        )
    }

    fun addMessage(message: MedicalMessage) {
        _messages.value += message
    }

}