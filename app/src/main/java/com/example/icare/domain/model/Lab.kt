package com.example.icare.domain.model


data class Lab(
    val imageUrl: String,
    val name: String,
    val address: String,
    val rating: Double,
    val reviewCount: Double
)