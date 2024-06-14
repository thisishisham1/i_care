package com.example.icare.model.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// clas check the network status
class NetworkStatusHelper(private val context: Context) {
    private val _isNetworkAvailable = MutableStateFlow(checkNetworkStatus())
    val isNetworkAvailable: StateFlow<Boolean> = _isNetworkAvailable

    private fun checkNetworkStatus(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}