package com.example.icare.domain.model

data class Pharmacy(
    override val title: String,
    val id: Int,
    val imageUrl: String,
    val name: String,
    val address: String,
    val rating: Double,
    val countReview: Double
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

val listOfPharmacy = (0 until 150).map {
    Pharmacy(
        title = "Pharmacy",
        id = it,
        imageUrl = "https://source.unsplash.com/random/200x200?pharmacy",
        name = "${faker.company().name()} Pharmacy",
        address = faker.address().fullAddress(),
        rating = (3..5).random().toDouble(),
        countReview = (50..200).random().toDouble()
    )
}