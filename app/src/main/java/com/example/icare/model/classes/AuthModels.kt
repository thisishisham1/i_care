package com.example.icare.model.classes

data class LoginRequest(val email: String, val password: String)
data class UserResponse(
    val img: String,
    val id: String,
    val email: String,
    val username: String,
    val gender: String,
    val isAdmin: Boolean,
    val role: String = "patient",
    val reservations: List<String>,
    val ratings: List<String>,
    val createdAt: String,
    val updatedAt: String,
    val v: Int,
    val token: String,
    val status: Boolean,
    val message: String?
)