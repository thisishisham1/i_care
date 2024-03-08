package com.example.icare.domain.model.enums

import com.example.icare.R

enum class ErrorTypes(val errorMessage: Int) {
    EMPTY(R.string.em_empty_filed),
    INVALID_EMAIL(R.string.em_Invalid_Email),
    INVALID_EMAIL_FORMAT(R.string.em_invalid_email_format),
    INVALID_PHONE_NUMBER(R.string.em_Invalid_phone),
    SHORT_PASSWORD(R.string.em_short_pass),
    PASSWORD_MISMATCH(R.string.em_mismatch),
    INCORRECT_PASSWORD(R.string.em_incorrect_pass),
    EMAIL_NOT_FOUNDED(R.string.em_email_not_found)
}