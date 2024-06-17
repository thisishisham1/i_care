package com.example.icare.repository

import com.example.icare.model.classes.LoginRequest
import com.example.icare.model.classes.UserResponse
import com.example.icare.model.network.apiService.RetrofitInstance

class AuthRepository {
    private val apiService = RetrofitInstance.apiService

    suspend fun loginUser(email: String, password: String): UserResponse {
        val request = LoginRequest(email, password)
        return apiService.login(request)
    }
}