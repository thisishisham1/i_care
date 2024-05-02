package com.example.icare.model.classes

import com.example.icare.R

enum class ErrorTypes(val errorMessage: Int) {
    EMPTY(R.string.empty_field_error),
    INVALID_EMAIL(R.string.invalid_email_error),
    INVALID_EMAIL_FORMAT(R.string.invalid_email_format_error),
    INVALID_PHONE_NUMBER(R.string.invalid_phone_error),
    SHORT_PASSWORD(R.string.short_password_error),
    PASSWORD_MISMATCH(R.string.password_mismatch_error),
    INCORRECT_PASSWORD(R.string.incorrect_password_error),
    EMAIL_NOT_FOUNDED(R.string.email_not_found_error)
}