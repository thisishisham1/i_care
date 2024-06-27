package com.example.icare.view.registeration.login

sealed class LoginUIEvent {

    data class EmailChanged(val email: String) : LoginUIEvent()
    data class PasswordChanged(val password: String) : LoginUIEvent()

    data object LoginButtonClicked : LoginUIEvent()
    data object GoogleButtonClick : LoginUIEvent()
    data object ForgotPasswordClick : LoginUIEvent()
    data object SignUpClick : LoginUIEvent()
}