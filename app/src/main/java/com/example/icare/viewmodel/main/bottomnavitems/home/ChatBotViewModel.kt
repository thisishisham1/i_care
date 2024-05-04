package com.example.icare.viewmodel.main.bottomnavitems.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.icare.model.classes.Message
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ChatBotState(
    val messages: List<Message> = emptyList(),
    val isBotTyping: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false
)

class ChatBotViewModel : ViewModel() {
    private val _message = MutableStateFlow<String>("")
    val message: StateFlow<String> = _message

    private val _chatBotState = MutableStateFlow(ChatBotState())
    val chatBotState: StateFlow<ChatBotState> = _chatBotState


    fun onChangeValue(contentMessage: String) {
        _message.value = contentMessage
        // TODO: handle when value change
    }

    fun onAttachmentClicked() {
        // TODO: handle attachment button
    }

    fun onSendClicked(contentMessage: String) {
        val currentTimeMillis = System.currentTimeMillis()
        val newMessage = Message("Me", "Bot", _message.value, currentTimeMillis)
        _chatBotState.update { it.copy(messages = it.messages + newMessage, isBotTyping = true) }
        viewModelScope.launch {
            try {
                delay(2000)
                val botMessage = Message(
                    "Bot", "Me", "Bot response to $contentMessage", System.currentTimeMillis()
                )
                _chatBotState.update {
                    it.copy(
                        messages = it.messages + botMessage,
                        isBotTyping = false,
                    )
                }
            } catch (e: Exception) {
                _chatBotState.update {
                    it.copy(isError = true)
                }
            }
        }
    }

    fun onCameraClicked() {
        // TODO:  handle camera click
    }
}