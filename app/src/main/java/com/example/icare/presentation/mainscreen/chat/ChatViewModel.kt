package com.example.icare.presentation.mainscreen.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.icare.domain.model.Message

class ChatViewModel(val navController: NavController) : ViewModel() {
    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val initialMessage = Message("Me", "John", "How are you doing", 1675299320)

    val messages = mutableListOf(
        initialMessage,
        Message("Me", "John", "Hey there! I'm doing well, thanks for asking.", 1675299321),
        Message("John", "Me", "That's great to hear!", 1675299322),
        Message("Me", "John", "Yeah, I had a good day. What about you?", 1675299323),
        Message("John", "Me", "Not too bad, just busy with work.", 1675299324),
        Message("Me", "John", "I understand. Work can be exhausting sometimes.", 1675299325),
        Message("John", "Me", "Definitely. But it's all worth it in the end.", 1675299326),
        Message("Me", "John", "Absolutely!", 1675299327),
        Message(
            "John",
            "Me",
            "By the way, have you seen the new movie that just came out?",
            1675299328
        ),
        Message("Me", "John", "No, I haven't had the chance yet. Is it any good?", 1675299329),
        Message(
            "John",
            "Me",
            "It's getting great reviews. We should go see it together sometime.",
            1675299330
        ),
        Message("Me", "John", "That sounds like a plan! Let's do it.", 1675299331),
        Message("John", "Me", "Awesome! I'll check the showtimes and let you know.", 1675299332),
        Message("Me", "John", "Sounds good. Looking forward to it!", 1675299333),
        Message("John", "Me", "Me too!", 1675299334),
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


    fun handelSendClick(contentMessage: String) {
        val currentTimeMillis = System.currentTimeMillis()
        val newMessage = Message("Me", "John", contentMessage, currentTimeMillis)
        messages.add(newMessage)
        _message.value = ""
        // TODO: handle send message
    }


    fun onChangeValue(value: String) {
        _message.value = value
        // TODO: handle value Change
    }

    fun handleCameraClick() {
        // TODO: handle camera click
    }

    fun handleAttachmentClick() {
        // TODO: handle Attachment click
    }

}