package com.example.icare.model.classes.users

import com.github.javafaker.Faker

sealed interface Users {
    val title: String
    fun doseMatchQuery(query: String): Boolean
}

data class Doctor(
    override val title: String,
    val id: Int,
    val name: String,
    val imageUrl: String = "https://img.freepik.com/free-photo/3d-illustration-businessman-white-suit-glasses_1142-40726.jpg?t=st=1719115049~exp=1719118649~hmac=a64b452d624bdee2912a1f6bbddece8dc805c683b1b1489193ec388a3fad89d7&w=740",
    val filed: String,
    val address: String,
    val rating: Double,
    val countReviews: Double
) : Users {
    override fun doseMatchQuery(query: String): Boolean {
        val normalizedQuery = query.trim().lowercase() // Normalize the query forbetter matching

        val matchingCombinations = listOf(
            name.lowercase(), "${name.first()}", "${name.first()}${name.last()}"
        )

        return matchingCombinations.any { it.contains(normalizedQuery) }
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
        countReviews = (100..1000).random().toDouble(),
    )
}