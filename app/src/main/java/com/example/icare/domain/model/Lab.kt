package com.example.icare.domain.model


data class Lab(
    val id: Int,
    val imageUrl: String,
    val name: String,
    val address: String,
    val rating: Double,
    val reviewCount: Double
)

val listOfLabs = (1..10).map {
    Lab(
        id = it,
        "https://images.unsplash.com/photo-1532187863486-abf9dbad1b69?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        "Sunrise Health lab",
        "123 Oak Street, CA 98765",
        4.6,
        123.0
    )
}