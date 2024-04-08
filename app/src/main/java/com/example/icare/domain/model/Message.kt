package com.example.icare.domain.model

data class Message(
    val text: String,
    val sender: String,
    val timeStamp: Long=System.currentTimeMillis()
)