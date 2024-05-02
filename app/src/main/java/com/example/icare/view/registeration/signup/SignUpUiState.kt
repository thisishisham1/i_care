package com.example.icare.view.registeration.signup

data class SignUpUiState(
    var completeName: String = "",
    var email: String = "",
    val phone: String = "",
    var password: String = "",
    var privacyPolicyAccepted: Boolean = false,
    var confirmPassword: String = "",

    var confirmPasswordError: Boolean = false,
    var completeNameError: Boolean = false,
    var emailError: Boolean = false,
    val phoneError: Boolean = false,
    var passwordError: Boolean = false,
    var privacyPolicyError: Boolean = false,

    var errorMessageForEmail: String? = null
)