package com.example.icare.data.login

import android.util.Patterns

object Validator {


    fun validateCompleteName(name: String): ValidationResult {
        return ValidationResult(
            (name.isNotEmpty() && name.length >= 2)
        )

    }

    fun validatePhoneNumber(phone: String): ValidationResult {
        return ValidationResult(
            (phone.isNotEmpty() && Patterns.PHONE.matcher(phone).matches())
        )
    }

    fun validateEmail(email: String): ValidationResult {
        return ValidationResult(
            (email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches())
        )
    }

    fun validatePassword(password: String): ValidationResult {
        return ValidationResult(
            (password.isNotEmpty() && password.length >= 4)
        )
    }

    fun validateConfirmPassword(password: String, confirmPassword: String): ValidationResult {
        return ValidationResult(
            (confirmPassword == password)
        )
    }

    fun validatePrivacyPolicyAcceptance(statusValue: Boolean): ValidationResult {
        return ValidationResult(
            statusValue
        )
    }

}

data class ValidationResult(
    val status: Boolean = false
)