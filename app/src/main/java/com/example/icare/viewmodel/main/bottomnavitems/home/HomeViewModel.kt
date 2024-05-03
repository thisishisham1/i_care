package com.example.icare.viewmodel.main.bottomnavitems.home

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.icare.model.classes.Destinations
import com.example.icare.model.classes.Doctor
import com.example.icare.model.classes.Lab
import com.example.icare.model.classes.Pharmacy
import com.example.icare.model.classes.Users

class HomeViewModel(val navController: NavController) : ViewModel() {
    fun handleNavigationDetail(user: Users) {
        when (user) {
            is Doctor -> navController.navigate("${Destinations.Details.DoctorDetails.route}/${user.id}")
            is Pharmacy -> navController.navigate("${Destinations.Details.PharmacyDetails.route}/${user.id}")
            is Lab -> navController.navigate("${Destinations.Details.LabDetails.route}/${user.id}")
        }
    }

    fun handleNavigateDetails(doctor: Doctor) {
        navController.navigate("${Destinations.Details.DoctorDetails.route}/${doctor.id}")
    }

    fun handleClickAction(action: String) {
        when (action) {
            "Personal test" -> {
                // TODO: handel personal test click
            }

            "Chat Bot" -> {
                // todo: Handle settings action
            }

            "Labs" -> {
                navController.navigate(Destinations.Lists.Labs.route)
            }

            "Pharmacies" -> {
                navController.navigate(Destinations.Lists.Pharmacies.route)
            }
        }
    }

    fun handleClickSeeMore() = navController.navigate(Destinations.Lists.Doctors.route)

    fun handleNotificationClick() {
        // TODO: handle notification Click
    }

    fun handleChatsClick() {
        // TODO: handle chat Click
    }
}