package com.example.icare.model.classes.users


data class Lab(
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

val listOfLabs = (0 until 150).map { index ->
    Lab(
        title = "Lab",
        id = index + 1,
        imageUrl = "https://images.unsplash.com/photo-1532187863486-abf9dbad1b69?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        name = "${faker.company().name()} Lab",
        address = faker.address().fullAddress(),
        rating = (3..5).random().toDouble(),
        countReview = (1000..3000).random().toDouble()
    )
}