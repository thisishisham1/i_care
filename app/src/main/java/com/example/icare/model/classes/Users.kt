package com.example.icare.model.classes

import com.github.javafaker.Faker

val faker = Faker()

sealed interface Users {
    val title: String
    fun doseMatchQuery(query: String): Boolean
}

abstract class User(
    override val title: String,
    open val id: Int,
    open val imageUrl: String,
    open val name: String,
    open val address: String,
    open val rating: Double,
    open val countReview: Int
) : Users {
    override fun doseMatchQuery(query: String): Boolean {
        val normalizedQuery = query.trim().lowercase()
        return name.lowercase().contains(normalizedQuery) || address.lowercase()
            .contains(normalizedQuery)
    }
}

data class Doctor(
    override val title: String = "Doctor",
    override val id: Int,
    override val imageUrl: String = "https://img.freepik.com/free-photo/3d-illustration-businessman-white-suit-glasses_1142-40726.jpg?t=st=1719115049~exp=1719118649~hmac=a64b452d624bdee2912a1f6bbddece8dc805c683b1b1489193ec388a3fad89d7&w=740",
    override val name: String,
    override val address: String,
    override val rating: Double,
    override val countReview: Int,
    val specialty: String

) : User(title, id, imageUrl, name, address, rating, countReview)

data class Lab(
    override val title: String = "Lab",
    override val id: Int,
    override val imageUrl: String = "https://img.freepik.com/free-psd/3d-background-with-scientist-experimenting-laboratory_23-2150099107.jpg?t=st=1719217328~exp=1719220928~hmac=7471c4f49d4ffab12e121b8a3de7a66a7945ad01d7dcb414e8a047790d6bd96e&w=740",
    override val name: String,
    override val address: String,
    override val rating: Double,
    override val countReview: Int
) : User(title, id, imageUrl, name, address, rating, countReview)

data class Pharmacy(
    override val title: String = "Pharmacy",
    override val id: Int,
    override val imageUrl: String = "https://img.freepik.com/free-photo/portrait-3d-male-doctor_23-2151106665.jpg?t=st=1719217446~exp=1719221046~hmac=156f9bb0522558f584138b84a6cbd145ce0a70cb4bdf86374b8b58212f28672f&w=1060",
    override val name: String,
    override val address: String,
    override val rating: Double,
    override val countReview: Int
) : User(title, id, imageUrl, name, address, rating, countReview)

val listOfDoctor = (0 until 150).map { index ->
    Doctor(
        id = index + 1,
        name = faker.name().fullName(),
        specialty = faker.job().field().lowercase(),
        address = faker.address().fullAddress(),
        rating = (3..5).random().toDouble(),
        countReview = (100..1000).random(),
    )
}
val listOfLabs = (0 until 150).map { index ->
    Lab(
        title = "Lab",
        id = index + 1,
        name = "${faker.company().name()} Lab",
        address = faker.address().fullAddress(),
        rating = (3..5).random().toDouble(),
        countReview = (1000..3000).random()
    )
}
val listOfPharmacy = (0 until 150).map {
    Pharmacy(
        title = "Pharmacy",
        id = it,
        name = "${faker.company().name()} Pharmacy",
        address = faker.address().fullAddress(),
        rating = (3..5).random().toDouble(),
        countReview = (50..200).random()
    )
}