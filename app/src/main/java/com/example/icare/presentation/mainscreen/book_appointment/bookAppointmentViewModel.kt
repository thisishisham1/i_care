package com.example.icare.presentation.mainscreen.book_appointment

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class BookAppointmentVieModel(val navController: NavController) : ViewModel() {
    fun handleBackArrow() {
        navController.navigateUp()
    }
}