package com.example.icare.viewmodel.main.bottomnavitems.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.icare.model.classes.ChatBotRequest
import com.example.icare.model.classes.Message
import com.example.icare.repository.ChatBotRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ChatBotState(
    val messages: List<Message> = emptyList(),
    val isBotTyping: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)

class ChatBotViewModel : ViewModel() {
    private val _userInput = MutableStateFlow<String>("")
    val userInput: StateFlow<String> = _userInput

    private val _chatBotState = MutableStateFlow(ChatBotState())
    val chatBotState: StateFlow<ChatBotState> = _chatBotState

    private val chatBotRepository = ChatBotRepository()


    fun onUserInputChanged(newInput: String) {
        _userInput.value = newInput
    }

    fun onSendClicked(inputMessage: String) {
        if (_userInput.value.isBlank()) return
        val userMessage = Message("Me", "Bot", inputMessage, System.currentTimeMillis())
        _chatBotState.update { it.copy(messages = it.messages + userMessage) }
        viewModelScope.launch {
            try {
                _chatBotState.update {
                    it.copy(
                        isBotTyping = true,
                        isLoading = true
                    )
                }
                delay(2000)
                Log.d("Bot", _userInput.value)
                val botResponse = chatBotRepository.getDiagnosis(ChatBotRequest(inputMessage))
                Log.d("Bot Response", botResponse)
                val botMessage = Message(
                    "Bot", "Me", botResponse, System.currentTimeMillis()
                )
                _chatBotState.update {
                    it.copy(
                        messages = it.messages + botMessage,
                        isBotTyping = false,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _chatBotState.update {
                    it.copy(isBotTyping = false, error = e.message ?: "An error occurred")
                }
            }

        }
    }
}