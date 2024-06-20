package com.example.icare.view.registeration.login

data class LoginUIState(
    val email: String = "",
    val password: String = "",

    val isEmailError: Boolean = false,
    val isPasswordError: Boolean = false,

    val emailError: String? = null,
    val passwordError: String? = null
)