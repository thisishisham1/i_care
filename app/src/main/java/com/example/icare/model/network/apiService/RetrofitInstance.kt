package com.example.icare.model.network.apiService

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitInstance {
    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS) //Set connect timeout
        .readTimeout(10, TimeUnit.SECONDS) // Set read timeout
        .writeTimeout(10, TimeUnit.SECONDS) // Set write timeout
        .build()

    val apiService: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://icare48.000webhostapp.com/").client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()

        retrofit.create(ApiService::class.java)
    }
    val apiServiceChatBot: ApiServiceChatBot by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://icarechatbot-production.up.railway.app/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        retrofit.create(ApiServiceChatBot::class.java)
    }
}