package com.example.icare.presentation.mainscreen.screenDetails

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.icare.domain.model.listOfDoctor

class DetailsViewModel(val navController: NavController) : ViewModel() {
    fun getDoctorDetails(doctorId: Int) = listOfDoctor.first { it.id == doctorId }
    fun handleBackArrow() {
        navController.navigateUp()
    }

    fun handelBookButton() {
        // TODO: handel book button
    }
}