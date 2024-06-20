package com.example.icare.view.registeration.signup


sealed class SignupUIEvent {

    data class CompleteNameChanged(val name: String) : SignupUIEvent()
    data class EmailChanged(val email: String) : SignupUIEvent()
    data class PhoneChanged(val phone: String) : SignupUIEvent()
    data class PasswordChanged(val password: String) : SignupUIEvent()
    data class PrivacyPolicyCheckBoxClicked(val status: Boolean) : SignupUIEvent()

    data object RegisterButtonClicked : SignupUIEvent()
}