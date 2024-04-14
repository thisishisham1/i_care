package com.example.icare.presentation.mainscreen.screens.home.category.chatbot

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.icare.domain.model.Message

class ChatBotViewModel : ViewModel() {
    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val initialMessage = Message("Me", "Health", "How are you doing", 1675299320)

    val messages = mutableListOf(
        initialMessage,
        Message("Me", "Health", "Hey there! I'm doing well, thanks for asking.", 1675299321),
        Message("Health", "Me", "That's great to hear!", 1675299322),
        Message("Me", "Health", "Yeah, I had a good day. What about you?", 1675299323),
        Message("Health", "Me", "Not too bad, just busy with work.", 1675299324),
        Message("Me", "Health", "I understand. Work can be exhausting sometimes.", 1675299325),
        Message("Health", "Me", "Definitely. But it's all worth it in the end.", 1675299326),
        Message("Me", "Health", "Absolutely!", 1675299327),
        Message(
            "Health",
            "Me",
            "By the way, have you seen the new movie that just came out?",
            1675299328
        ),
        Message("Me", "Health", "No, I haven't had the chance yet. Is it any good?", 1675299329),
        Message(
            "Health",
            "Me",
            "It's getting great reviews. We should go see it together sometime.",
            1675299330
        ),
        Message("Me", "Health", "That sounds like a plan! Let's do it.", 1675299331),
        Message("Health", "Me", "Awesome! I'll check the showtimes and let you know.", 1675299332),
        Message("Me", "Health", "Sounds good. Looking forward to it!", 1675299333),
        Message("Health", "Me", "Me too!", 1675299334),
        Message(
            "Me",
            "Health",
            "Anyway, got to get back to work now. Talk to you later!",
            1675299335
        ),
        Message("Health", "Me", "Sure, catch you later!", 1675299336),
        Message("Me", "Health", "Take care!", 1675299337),
        Message("Health", "Me", "You too!", 1675299338),
        Message("Me", "Health", "Goodbye!", 1675299339)
    )

    fun onValueChange(contentMessage: String) {
        val currentTimeMillis = System.currentTimeMillis()
        val newMessage = Message("Me", "John", contentMessage, currentTimeMillis)
        messages.add(newMessage)
        _message.value = ""
        // TODO: handle when value change
    }

    fun handleAttachmentButton() {
        // TODO: handle attachment button
    }

    fun handleSendClick(contentMessage:String){
        // TODO: handel sendClick
    }
}