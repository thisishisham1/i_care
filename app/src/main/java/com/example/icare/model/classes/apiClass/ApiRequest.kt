package com.example.icare.model.classes.apiClass

data class LoginRequest(val email: String, val pass: String)

data class RegisterRequest(
    val name1: String, val email: String, val phone: String, val pass: String
)

data class ChatBotRequest(
    val text: String
)

data class ReservationRequest(
    val patient_id: Int,
    val clinic_id: Int,
    val name: String,
    val phone: String
)