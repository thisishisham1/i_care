package com.example.icare.model.network.apiService

import com.example.icare.model.classes.apiClass.ChatBotRequest
import com.example.icare.model.classes.apiClass.ClinicReservation
import com.example.icare.model.classes.apiClass.LoginRequest
import com.example.icare.model.classes.apiClass.RegisterRequest
import com.example.icare.model.classes.apiClass.ReservationRequest
import com.example.icare.model.classes.apiClass.ReservationResponse
import com.example.icare.model.classes.apiClass.UserResponse
import com.example.icare.model.classes.apiClass.UsersResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("/api/patient/login")
    suspend fun login(@Body request: LoginRequest): UserResponse

    @POST("/api/patient/register")
    suspend fun register(@Body request: RegisterRequest): UserResponse

    @GET("/api/clinics/category/Clinic")
    suspend fun getClinic(): List<UsersResponse>

    @GET("/api/clinics/category/pharmacy")
    suspend fun getPharmacies(): List<UsersResponse>

    @GET("/api/clinics/category/laboratory")
    suspend fun getLabs(): List<UsersResponse>

    @POST("/api/clinicreservation/store")
    suspend fun insertReservation(@Body request: ReservationRequest): ReservationResponse

    @GET("api/clinicreservation/showinpatient/{patientId}")
    suspend fun getClinicReservations(@Path("patientId") patientId: Int): List<ClinicReservation>
}

interface ApiServiceChatBot {
    @POST("/predict")
    suspend fun getDiagnosis(@Body request: List<ChatBotRequest>): String
}