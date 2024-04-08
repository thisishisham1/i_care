package com.example.icare.presentation.mainscreen.screens.home.category.chatbot

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChatBotViewModel : ViewModel() {
    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    init {
        _message.value = "Hi! I'm your health assistant."
    }
}