package com.example.icare.presentation.mainscreen.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.icare.core.util.Destinations
import com.example.icare.domain.model.Doctor
import com.example.icare.domain.model.Lab
import com.example.icare.domain.model.Pharmacy

class SearchViewModel(val navController: NavController) : ViewModel() {
    val valueState = mutableStateOf("")
    fun onValueChange(newValue: String) {
        valueState.value = newValue
    }

    fun handleClickDoctor(doctor: Doctor) {
        navController.navigate("${Destinations.DoctorDetails.route}/${doctor.id}")
    }

    fun handleClickPharmacy(pharmacy: Pharmacy) =
        navController.navigate("${Destinations.PharmacyDetails.route}/${pharmacy.id}")

    fun handleClickLab(lab: Lab) =
        navController.navigate("${Destinations.LabDetails.route}/${lab.id}")
}