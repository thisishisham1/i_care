package com.example.icare.domain.model

import com.github.javafaker.Faker

sealed interface Users {
    val title: String
    fun doseMatchQuery(query: String): Boolean
}

data class Doctor(
    override val title: String,
    val id: Int,
    val name: String,
    val imageUrl: String = "https://example.com/default_image.jpg",
    val filed: String,
    val address: String,
    val rating: Double,
    val countReviews: Double
) : Users {
    override fun doseMatchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            name, "${name.first()}"
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}

val faker = Faker()

val listOfDoctor = (0 until 150).map { index ->
    Doctor(
        title = "Doctor",
        id = index + 1,
        name = faker.name().fullName(),
        filed = faker.job().field().lowercase(),
        address = faker.address().fullAddress(),
        rating = (3..5).random().toDouble(),
        countReviews = (100..1000).random().toDouble()
    )
}