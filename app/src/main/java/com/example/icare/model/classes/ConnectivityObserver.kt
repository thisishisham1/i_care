package com.example.icare.model.classes

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    fun observe(): Flow<Status>
    enum class Status {
        AVAILABLE, UNAVAILABLE
    }

}