package com.example.icare.repository

import android.util.Log
import com.example.icare.MyApplication
import com.example.icare.model.classes.apiClass.AuthError
import com.example.icare.model.classes.apiClass.ChatBotRequest
import com.example.icare.model.classes.apiClass.ClinicReservation
import com.example.icare.model.classes.apiClass.LoginRequest
import com.example.icare.model.classes.apiClass.RegisterRequest
import com.example.icare.model.classes.apiClass.ReservationRequest
import com.example.icare.model.classes.apiClass.ReservationResponse
import com.example.icare.model.classes.apiClass.UserResponse
import com.example.icare.model.classes.apiClass.UsersResponse
import com.example.icare.model.local.UserDatabase
import com.example.icare.model.network.apiService.RetrofitInstance
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import retrofit2.HttpException

class AuthRepository {
    private val apiService = RetrofitInstance.apiService

    suspend fun loginUser(request: LoginRequest): Result<UserResponse> {
        return try {
            val response = apiService.login(request)
            UserDatabase.getDatabase(MyApplication.applicationContext()).userResponseDao()
                .insertUser(response)
            Result.success(response)
        } catch (e: HttpException) {
            Result.failure(parseError(e))
        }
    }

    suspend fun registerUser(request: RegisterRequest): Result<UserResponse> {
        return try {
            val response = apiService.register(request)
            UserDatabase.getDatabase(MyApplication.applicationContext()).userResponseDao()
                .insertUser(response)
            Result.success(response)
        } catch (e: HttpException) {
            Result.failure(parseError(e))
        }
    }

    private fun parseError(e: HttpException): AuthError {
        val errorJsonString = e.response()?.errorBody()?.string()
        val gson = Gson()
        val errorMessage = try {
            gson.fromJson(errorJsonString, UserResponse::class.java)?.message
        } catch (ex: JsonSyntaxException) {
            null
        }
        return when (e.code()) {
            400 -> AuthError.BadRequest(errorMessage ?: "Bad request")
            401 -> AuthError.Unauthorized
            403 -> AuthError.Forbidden
            404 -> AuthError.NotFound
            500 -> AuthError.InternalServerError
            else -> AuthError.Unknown("An unknown error occurred with code: ${e.code()}")
        }
    }

    suspend fun logout() {
        val userDao = UserDatabase.getDatabase(MyApplication.applicationContext()).userResponseDao()
        userDao.deleteUser()
        userDao.clearReservation()
    }


}

class ChatBotRepository {
    private val apiService = RetrofitInstance.apiServiceChatBot

    suspend fun getDiagnosis(request: ChatBotRequest): String {
        val response = apiService.getDiagnosis(listOf(request))
        return response
    }
}

class UsersRepository {
    private val apiService = RetrofitInstance.apiService

    suspend fun getClinics(): Result<List<UsersResponse>> {
        return try {
            val response = apiService.getClinic()
            Result.success(response)
        } catch (e: HttpException) {
            Log.e("", "Error fetching clinics: ${e.message}")
            Result.failure(e)
        }
    }

    suspend fun getPharmacies(): Result<List<UsersResponse>> {
        return try {
            val response = apiService.getPharmacies()
            Log.d("UserRepository", "succeed : ${response.size}")
            Result.success(response)
        } catch (e: HttpException) {
            Log.e("UsersRepository", "Error fetching pharmacy: ${e.message}")
            Result.failure(e)
        }
    }

    suspend fun getLabs(): Result<List<UsersResponse>> {
        return try {
            val response = apiService.getLabs()
            Result.success(response)
        } catch (e: HttpException) {
            Log.e("UsersRepository", "Error fetching labs: ${e.message}")
            Result.failure(e)
        }
    }

    suspend fun insertReservation(request: ReservationRequest): Result<ReservationResponse> {
        return try {
            val response = apiService.insertReservation(request)
            Log.d("UsersRepository", "$response")
            Result.success(response)
        } catch (e: HttpException) {
            Log.e("UsersRepository", "HTTP error inserting reservation: ${e.message}")
            Result.failure(e)
        } catch (e: Exception) {
            Log.e("UsersRepository", "Unknown error inserting reservation: ${e.message}")
            Result.failure(e)
        }
    }

    suspend fun getClinicReservation(patientId: Int): Result<List<ClinicReservation>> {
        return try {
            val reservations = apiService.getClinicReservations(patientId)
            reservations.forEach { reservation ->
                UserDatabase.getDatabase(MyApplication.applicationContext()).userResponseDao()
                    .insertReservation(reservation)
            }
            Log.d("GetClinicReservation", reservations.size.toString())
            Result.success(reservations)
        } catch (e: Exception) {
            Log.e("GetClinicReservation", e.message.toString())
            Result.failure(e)
        }
    }

}