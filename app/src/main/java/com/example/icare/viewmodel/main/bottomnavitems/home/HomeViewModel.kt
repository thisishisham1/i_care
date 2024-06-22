package com.example.icare.viewmodel.main.bottomnavitems.home

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.icare.model.classes.Destinations
import com.example.icare.model.classes.users.Doctor
import com.example.icare.model.classes.users.Lab
import com.example.icare.model.classes.users.Pharmacy
import com.example.icare.model.classes.users.Users

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
            "Personal\ntest" -> {
                navController.navigate("${Destinations.WebView.WebViewScreen.route}/${Uri.encode("https://icarembti.streamlit.app/")}")
            }

            "Chat Bot" -> {
                navController.navigate(Destinations.Chat.ChatBot.route)
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