package com.example.icare.presentation.mainscreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.icare.core.util.Destinations
import com.example.icare.presentation.mainscreen.screenDetails.DoctorDetails
import com.example.icare.presentation.mainscreen.screens.appointment.AppointmentScreen
import com.example.icare.presentation.mainscreen.screens.home.HomeScreen
import com.example.icare.presentation.mainscreen.screens.profile.ProfileScreen
import com.example.icare.presentation.mainscreen.screens.search.SearchScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") {
            HomeScreen(navController = navController)
        }
        composable("Search") {
            SearchScreen(navController = navController)
        }
        composable("Appointment") {
            AppointmentScreen(navController = navController)
        }
        composable("Profile") {
            ProfileScreen(navController = navController)
        }
        composable("${Destinations.DoctorDetails.route}/{doctorId}") { NavBackStackEntry ->
            val doctorId = NavBackStackEntry.arguments?.getString("doctorId")?.toIntOrNull() ?: 0
            DoctorDetails(doctorId = doctorId)
        }
    }
}