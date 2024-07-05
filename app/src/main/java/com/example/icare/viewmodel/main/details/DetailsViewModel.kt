package com.example.icare.viewmodel.main.details

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.icare.MainViewModel
import com.example.icare.model.classes.Destinations
import com.example.icare.model.classes.UsersJson

class DetailsViewModel(val navController: NavController, private val mainViewModel: MainViewModel) :
    ViewModel() {
    fun handleBookButtonClick(user: UsersJson) {
        // Handle booking logic for all user types based on user.category
        when (user.category) {
            "Doctor" -> { /* Doctor booking logic */
            }

            "Lab" -> { /* Lab booking logic */
            }

            "Pharmacy" -> { /* Pharmacy booking logic */
            }
        }
    }

    fun getDoctorDetails(doctorId: Int) = mainViewModel.clinics.value?.first { it.id == doctorId }

    fun getPharmacyDetails(pharmacyId: Int) =
        mainViewModel.pharmacies.value?.first { it.id == pharmacyId }

    fun getLabDetails(labId: Int) = mainViewModel.labs.value?.first { it.id == labId }

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