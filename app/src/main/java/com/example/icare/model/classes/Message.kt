package com.example.icare.model.classes

data class Message(
    val senderId: String,
    val receiverId: String,
    val content: String,
    val timestamp: Long
)