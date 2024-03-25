package com.example.icare.presentation.mainscreen.screenDetails

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.icare.core.util.Destinations
import com.example.icare.domain.model.listOfDoctor
import com.example.icare.domain.model.listOfLabs
import com.example.icare.domain.model.listOfPharmacy

class DetailsViewModel(val navController: NavController) : ViewModel() {
    fun getDoctorDetails(doctorId: Int) = listOfDoctor.first { it.id == doctorId }
    fun getPharmacyDetails(pharmacyId: Int) = listOfPharmacy.first { it.id == pharmacyId }
    fun getLabDetails(labId: Int) = listOfLabs.first { it.id == labId }

    fun handleBackArrow() {
        navController.navigateUp()
    }

    fun handelDoctorButton() {
        navController.navigate(Destinations.BookAppointment.route)
    }

    fun handleLabButton() {
        // TODO: handel order button
    }
}