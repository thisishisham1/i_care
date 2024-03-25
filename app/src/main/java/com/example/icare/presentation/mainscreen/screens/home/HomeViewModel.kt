package com.example.icare.presentation.mainscreen.screens.home

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.icare.core.util.Destinations
import com.example.icare.domain.model.Doctor

class HomeViewModel(val navController: NavController) : ViewModel() {
    fun handleNavigateDetails(doctor: Doctor) {
        navController.navigate("${Destinations.DoctorDetails.route}/${doctor.id}")
    }

    fun handleNotificationClick() {
        // TODO: handle notification Click
    }

    fun handleChatsClick() {
        // TODO: handle chat Click
    }
}