package com.example.icare.view.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.icare.MainViewModel
import com.example.icare.model.sharedPreferences.PreferencesHelper
import com.example.icare.repository.UsersRepository
import com.example.icare.view.main.bottomnavitems.appointment.AppointmentScreen
import com.example.icare.view.main.bottomnavitems.home.HomeScreen
import com.example.icare.view.main.bottomnavitems.profile.ProfileScreen
import com.example.icare.view.main.bottomnavitems.search.SearchScreen

private val destinations = listOf(
    BottomNavItems.Home,
    BottomNavItems.Search,
    BottomNavItems.Appointment, BottomNavItems.Profile
)

@Composable
fun MainView(
    navController: NavController,
    preferencesHelper: PreferencesHelper,
) {
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
            Content(
                navController = navController,
                selectedIndex = selectedIndex,
                preferencesHelper
            )
        }
    }
}

@Composable
private fun Content(
    navController: NavController,
    selectedIndex: Int,
    preferencesHelper: PreferencesHelper
) {
    val vm = remember { MainViewModel(UsersRepository()) }
    when (selectedIndex) {
        0 -> HomeScreen(navController = navController, vm)
        1 -> SearchScreen(navController = navController, vm)
        2 -> AppointmentScreen()
        3 -> ProfileScreen(navController = navController, preferencesHelper)
        else -> {}
    }

}