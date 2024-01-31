package com.example.icare.model

enum class Users(val roleDescription: String) {
    PATIENT(roleDescription = "I am a Patient"),
    DOCTOR(roleDescription = "I am a Doctor"),
    PHARMACY(roleDescription = "I am a Pharmacy"),
    LABORATORY(roleDescription = "I am a Laboratory")
}