package com.example.icare.view.registeration.signup

data class SignUpUiState(
    var completeName: String = "",
    var email: String = "",
    val phone: String = "",
    var password: String = "",
    var privacyPolicyAccepted: Boolean = false,

    var isNameError: Boolean = false,
    var isEmailError: Boolean = false,
    val isPhoneError: Boolean = false,
    var isPasswordError: Boolean = false,
    var isPrivacyPolicyError: Boolean = false,

    val nameError: String? = null,
    val emailError: String? = null,
    val phoneError: String? = null,
    val passwordError: String? = null

)
