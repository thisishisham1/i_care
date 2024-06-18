package com.example.icare.repository

import com.example.icare.model.classes.LoginRequest
import com.example.icare.model.classes.RegisterRequest
import com.example.icare.model.classes.UserResponse
import com.example.icare.model.network.apiService.RetrofitInstance

class AuthRepository {
    private val apiService = RetrofitInstance.apiService

    suspend fun loginUser(request: LoginRequest): UserResponse {
        return apiService.login(request)
    }

    suspend fun registerUser(request: RegisterRequest): UserResponse {
        return apiService.register(request)
    }
}