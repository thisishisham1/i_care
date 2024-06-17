package com.example.icare.model.network.apiService

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val apiService: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://vezeeta-1.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        retrofit.create(ApiService::class.java)
    }
}