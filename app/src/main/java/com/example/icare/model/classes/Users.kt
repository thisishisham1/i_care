package com.example.icare.model.classes

data class UsersJson(
    val first_name: String,
    val last_name: String,
    val specialty: String?,
    val address: String?,
    val work_time: String?,
    val experience: Int?,
    val description: String?,
    val img: String? = null,
    val email: String,
    val phone_number: String,
    val pass: String,
    val category: String,
    val id: Int,
    val created_at: String,
    val updated_at: String
) {
    val effectiveImg: String
        get() = img
            ?: "https://img.freepik.com/free-photo/portrait-3d-male-doctor_23-2151107212.jpg?t=st=1720138180~exp=1720141780~hmac=f779d6ff4fc3965f73210d6d79a2a8575570752c96d67750498f2fb950df4954&w=740"
}

fun UsersJson.doesMatchQuery(query: String): Boolean {
    val normalizedQuery = query.trim().lowercase()
    var score = 0

    // Prioritize matches on certain fields
    if (first_name.lowercase().contains(normalizedQuery)) score += 3
    if (last_name.lowercase().contains(normalizedQuery)) score += 2
    if (specialty?.lowercase()?.contains(normalizedQuery) == true) score += 2

    // Check for partial matches in other fields
    if (address?.lowercase()?.contains(normalizedQuery) == true) score += 1
    if (description?.lowercase()?.contains(normalizedQuery) == true) score += 1

    // Return true if any match is found (you can adjust the threshold)
    return score > 0
}