package com.example.icare.viewmodel.main.book

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class BookAppointmentVieModel(val navController: NavController) : ViewModel() {
    fun handleBackArrow() {
        navController.navigateUp()
    }
}