package com.example.icare.model.classes

import androidx.room.Entity
import androidx.room.PrimaryKey

data class LoginRequest(val email: String, val pass: String)
data class RegisterRequest(
    val name1: String, val email: String, val phone: String, val pass: String
)

@Entity(tableName = "user_table")
data class UserResponse(
    val status: Boolean,
    val name1: String,
    val phone: String,
    val email: String,
    val updated_at: String,
    val created_at: String,
    @PrimaryKey val id: Int,
    val patient_token: String,
    val message: String?,
    val img: String
)

// Improved code:
sealed class AuthError(override val message: String? = null) : Throwable(message) {
    data class BadRequest(val error: String) : AuthError(error)
    object Unauthorized : AuthError("Unauthorized access.")
    object Forbidden : AuthError("Access is forbidden.")
    object NotFound : AuthError("Resource not found.")
    object InternalServerError : AuthError("Internal server error.")
    data class Unknown(val error: String) : AuthError(error)
}
