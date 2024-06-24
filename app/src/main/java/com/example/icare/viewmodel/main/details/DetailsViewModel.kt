package com.example.icare.viewmodel.main.details

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.icare.model.classes.Destinations
import com.example.icare.model.classes.listOfDoctor
import com.example.icare.model.classes.listOfLabs
import com.example.icare.model.classes.listOfPharmacy

class DetailsViewModel(val navController: NavController) : ViewModel() {
    fun getDoctorDetails(doctorId: Int) = listOfDoctor.first { it.id == doctorId }
    fun getPharmacyDetails(pharmacyId: Int) = listOfPharmacy.first { it.id == pharmacyId }
    fun getLabDetails(labId: Int) = listOfLabs.first { it.id == labId }

    fun handleBackArrow() {
        navController.navigateUp()
    }

    fun handelDoctorButton() {
        navController.navigate(Destinations.Appointment.BookAppointment.route)
    }

    fun handleLabButton() {
        // TODO: handel order button
    }

    fun handleDoctorBookButtonClick() {
        TODO("Not yet implemented")
    }

    fun handleLabBookButtonClick() {
        TODO("Not yet implemented")
    }

    fun handlePharmacyBookButtonClick() {
        TODO("Not yet implemented")
    }
}