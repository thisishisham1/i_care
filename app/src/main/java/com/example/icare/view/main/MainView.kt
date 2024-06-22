package com.example.icare.view.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.icare.model.sharedPreferences.PreferencesHelper
import com.example.icare.view.main.bottomnavitems.appointment.AppointmentScreen
import com.example.icare.view.main.bottomnavitems.home.HomeScreen
import com.example.icare.view.main.bottomnavitems.profile.ProfileScreen
import com.example.icare.view.main.bottomnavitems.search.SearchScreen

private val destinations = arrayOf(
    BottomNavItems.Home,
    BottomNavItems.Search,
    BottomNavItems.Appointment, BottomNavItems.Profile
)

@Composable
fun MainView(navController: NavController, preferencesHelper: PreferencesHelper) {
    var selectedIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    Scaffold(bottomBar = {
        BottomNavBar(
            items = destinations,
            selectedIndex = selectedIndex,
            onItemSelected = { newIndex ->
                selectedIndex = newIndex
            })
    }) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Content(navController = navController, selectedIndex = selectedIndex, preferencesHelper)
        }
    }
}

@Composable
private fun Content(
    navController: NavController,
    selectedIndex: Int,
    preferencesHelper: PreferencesHelper
) {
    when (selectedIndex) {
        0 -> HomeScreen(navController = navController)
        1 -> SearchScreen(navController = navController)
        2 -> AppointmentScreen()
        3 -> ProfileScreen(navController = navController, preferencesHelper)
        else -> {}
    }

}