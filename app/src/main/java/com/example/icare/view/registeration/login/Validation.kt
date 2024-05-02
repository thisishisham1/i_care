package com.example.icare.view.registeration.login

import android.util.Patterns

object SignUpValidator {
    fun validateCompleteName(name: String): ValidationResult {
        return ValidationResult(
            (name.trim().split("\\s+".toRegex()).size >= 2),
        )

    }

    fun validatePhoneNumber(phone: String): ValidationResult {
        return ValidationResult(
            (phone.isNotEmpty() && Patterns.PHONE.matcher(phone).matches()),
        )
    }

    fun validateEmail(email: String): ValidationResult {
        return ValidationResult(
            (email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()),
        )
    }

    fun validatePassword(password: String): ValidationResult {
        return ValidationResult(
            (password.length >= 8),
        )
    }

    fun validateConfirmPassword(password: String, confirmPassword: String): ValidationResult {
        return ValidationResult(
            (confirmPassword == password),
        )
    }

    fun validatePrivacyPolicyAcceptance(statusValue: Boolean): ValidationResult {
        return ValidationResult(
            statusValue,
        )
    }

}

object LoginValidator {
    fun validateEmail(email: String): ValidationResult {
        return ValidationResult(
            (email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()),
        )
    }

    fun validatePassword(password: String): ValidationResult {
        return ValidationResult(
            (password.length >= 8)
        )
    }
}

data class ValidationResult(
    val status: Boolean = false,
)