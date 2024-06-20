package com.example.icare.repository

import com.example.icare.model.classes.AuthError
import com.example.icare.model.classes.LoginRequest
import com.example.icare.model.classes.RegisterRequest
import com.example.icare.model.classes.UserResponse
import com.example.icare.model.network.apiService.RetrofitInstance
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import retrofit2.HttpException

class AuthRepository {
    private val apiService = RetrofitInstance.apiService

    suspend fun loginUser(request: LoginRequest): Result<UserResponse> {
        return try {
            Result.success(apiService.login(request))
        } catch (e: HttpException) {
            Result.failure(parseError(e))
        }
    }


    suspend fun registerUser(request: RegisterRequest): Result<UserResponse> {
        return try {
            Result.success(apiService.register(request))
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

}

