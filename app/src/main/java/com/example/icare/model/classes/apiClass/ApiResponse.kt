package com.example.icare.model.classes.apiClass

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "user_table")
data class UserResponse(
    val status: Boolean,
    val name1: String,
    val phone: String,
    val email: String,
    val updated_at: String,
    val created_at: String,
    @PrimaryKey val id: Int,
    val patient_token: String,
    val message: String?,
    val img: String
)

@Parcelize
data class UsersResponse(
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
) : Parcelable {
    val effectiveImg: String
        get() = img
            ?: "https://img.freepik.com/free-photo/portrait-3d-male-doctor_23-2151107212.jpg?t=st=1720138180~exp=1720141780~hmac=f779d6ff4fc3965f73210d6d79a2a8575570752c96d67750498f2fb950df4954&w=740"
}


data class ReservationResponse(
    val img: String,
    val patient_id: Int,
    val clinic_id: Int,
    val name: String,
    val state: String,
    val phone: String,
    val updated_at: String,
    val created_at: String,
    val id: Int
)

@Entity(tableName = "reservation_users", primaryKeys = ["patient_id", "clinic_id"])
data class ClinicReservation(
    val id: Int,
    val clinic_id: Int,
    val patient_id: Int,
    val name: String,
    val phone: String,
    val created_at: String,
    val updated_at: String,
    val state: String,
    val img: String
)

fun UsersResponse.doesMatchQuery(query: String): Boolean {
    val normalizedQuery = query.trim().lowercase()
    // Simplified and more efficient query matching
    return first_name.lowercase().contains(normalizedQuery) ||
            last_name.lowercase().contains(normalizedQuery) ||
            specialty?.lowercase()?.contains(normalizedQuery) == true ||
            address?.lowercase()?.contains(normalizedQuery) == true ||
            description?.lowercase()?.contains(normalizedQuery) == true
}


sealed class AuthError(override val message: String? = null) : Throwable(message) {
    data class BadRequest(val error: String) : AuthError(error)
    data object Unauthorized : AuthError("Unauthorized access.") {
        private fun readResolve(): Any = Unauthorized
    }

    data object Forbidden : AuthError("Access is forbidden.") {
        private fun readResolve(): Any = Forbidden
    }

    data object NotFound : AuthError("Resource not found.") {
        private fun readResolve(): Any = NotFound
    }

    data object InternalServerError : AuthError("Internal server error.") {
        private fun readResolve(): Any = InternalServerError
    }

    data class Unknown(val error: String) : AuthError(error)
}

