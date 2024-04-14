package com.example.icare.presentation.mainscreen.chat

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.icare.domain.model.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChatViewModel(val navController: NavController) : ViewModel() {
    private val _message = MutableStateFlow("")
    val message = _message.asStateFlow()
    private val initialMessage = Message("How are you doing?", "Me", 1675299320)

    val messages = mutableListOf(
        initialMessage,
        Message("Hey there! I'm doing well, thanks for asking.", "John", 1675299321),
        Message("That's great to hear!", "Me", 1675299322),
        Message("Yeah, I had a good day. What about you?", "John", 1675299323),
        Message("Not too bad, just busy with work.", "Me", 1675299324),
        Message("I understand. Work can be exhausting sometimes.", "John", 1675299325),
        Message("Definitely. But it's all worth it in the end.", "Me", 1675299326),
        Message("Absolutely!", "John", 1675299327),
        Message("By the way, have you seen the new movie that just came out?", "Me", 1675299328),
        Message("No, I haven't had the chance yet. Is it any good?", "John", 1675299329),
        Message("It's getting great reviews. We should go see it together sometime.", "Me", 1675299330),
        Message("That sounds like a plan! Let's do it.", "John", 1675299331),
        Message("Awesome! I'll check the showtimes and let you know.", "Me", 1675299332),
        Message("Sounds good. Looking forward to it!", "John", 1675299333),
        Message("Me too!", "Me", 1675299334),
        Message("Anyway, got to get back to work now. Talk to you later!", "Me", 1675299335),
        Message("Sure, catch you later!", "John", 1675299336),
        Message("Take care!", "Me", 1675299337),
        Message("You too!", "John", 1675299338),
        Message("Goodbye!", "Me", 1675299339)
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
    fun handleSendClick(){
        // TODO: handle send click
    }
}