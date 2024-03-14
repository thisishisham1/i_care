package com.example.icare.domain.model

import android.media.Rating

data class Pharmacy(
    val imageUrl:String,
    val name: String,
    val address: String,
    val rating: Double,
    val reviewCount: Double
)