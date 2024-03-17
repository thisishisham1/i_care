package com.example.icare.domain.model

data class Doctor(
    val id: Int,
    val name: String,
    val filed: String,
    val address: String,
    val rating: Double,
    val countReviews: Double
)

val listOfDoctor = (1..10).map {
    Doctor(
        id = it,
        "Hisham Mohamed",
        "Orthopedic Surgery",
        "Women's Clinic,Settle,USA",
        5.0,
        1567.0
    )
}