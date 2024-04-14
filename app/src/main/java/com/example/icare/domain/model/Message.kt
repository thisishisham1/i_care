package com.example.icare.domain.model

data class Message(
    val senderId: String,
    val receiverId: String,
    val content: String,
    val timestamp: Long
)