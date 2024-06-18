package com.example.icare.model.classes

data class LoginRequest(val email: String, val pass: String)
data class RegisterRequest(
    val name1: String,
    val email: String,
    val phone: String,
    val pass: String
)

data class UserResponse(
    val status: Boolean,
    val name1: String,
    val phone: String,
    val email: String,
    val pass: String,
    val updated_at: String,
    val created_at: String,
    val id: Int,
    val patient_token: String,
    val message: String?
)