package com.example.icare.model.network.apiService

import com.example.icare.model.classes.ChatBotRequest
import com.example.icare.model.classes.LoginRequest
import com.example.icare.model.classes.RegisterRequest
import com.example.icare.model.classes.UserResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/api/patient/login")
    suspend fun login(@Body request: LoginRequest): UserResponse

    @POST("/api/patient/register")
    suspend fun register(@Body request: RegisterRequest): UserResponse
}

interface ApiServiceChatBot {
    @POST("/predict")
    suspend fun getDiagnosis(@Body request: List<ChatBotRequest>): String
}