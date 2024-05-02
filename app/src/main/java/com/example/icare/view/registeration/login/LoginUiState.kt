package com.example.icare.view.registeration.login

data class LoginUIState(
    var email: String = "",
    var password: String = "",

    var emailError: Boolean = false,
    var passwordError: Boolean = false,

    var genericError: String? = null
)