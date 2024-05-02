package com.example.icare.viewmodel.main.chat

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.icare.model.classes.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChatViewModel(val navController: NavController) : ViewModel() {
    private val _message = MutableStateFlow("")
    val message = _message.asStateFlow()
    private val initialMessage = Message("Me", "John", "How are you doing?", 1675299320)

    val messages = mutableListOf(
        initialMessage,
        Message("John", "Me", "Hey there! I'm doing well, thanks for asking.", 1675299321),
        Message("Me", "John", "That's great to hear!", 1675299322),
        Message("John", "Me", "Yeah, I had a good day. What about you?", 1675299323),
        Message("Me", "John", "Not too bad, just busy with work.", 1675299324),
        Message("John", "Me", "I understand. Work can be exhausting sometimes.", 1675299325),
        Message("Me", "John", "Definitely. But it's all worth it in the end.", 1675299326),
        Message("John", "Me", "Absolutely!", 1675299327),
        Message(
            "Me",
            "John",
            "By the way, have you seen the new movie that just came out?",
            1675299328
        ),
        Message("John", "Me", "No, I haven't had the chance yet. Is it any good?", 1675299329),
        Message(
            "Me",
            "John",
            "It's getting great reviews. We should go see it together sometime.",
            1675299330
        ),
        Message("John", "Me", "That sounds like a plan! Let's do it.", 1675299331),
        Message("Me", "John", "Awesome! I'll check the show times and let you know.", 1675299332),
        Message("John", "Me", "Sounds good. Looking forward to it!", 1675299333),
        Message("Me", "John", "Me too!", 1675299334),
        Message(
            "Me",
            "John",
            "Anyway, got to get back to work now. Talk to you later!",
            1675299335
        ),
        Message("John", "Me", "Sure, catch you later!", 1675299336),
        Message("Me", "John", "Take care!", 1675299337),
        Message("John", "Me", "You too!", 1675299338),
        Message("Me", "John", "Goodbye!", 1675299339)
    )


    fun handleSendMessage(message: String) {
        // TODO: handle send message
    }

    fun handleArrowBack() =
        navController.navigateUp()

    fun onChangeValue(value: String) {
        _message.value = value
    }

    fun handleRecordClick() {
        // TODO: handle record click
    }

    fun handleCameraClick() {
        // TODO: handle camera click
    }

    fun handleAttachmentClick() {
        // TODO: handle Attachment click
    }

    fun handleSendClick() {
        // TODO: handle send click
    }
}