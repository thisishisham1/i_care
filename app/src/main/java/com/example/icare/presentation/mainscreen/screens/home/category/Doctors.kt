package com.example.icare.presentation.mainscreen.screens.home.category

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.icare.core.util.reusablecomponent.TopAppBar
import com.example.icare.domain.model.listOfDoctor
import com.example.icare.presentation.mainscreen.screens.home.UserCard
import com.example.icare.presentation.mainscreen.screens.home.HomeViewModel

@Composable
fun DoctorsScreen(navController: NavController) {
    val homeViewModel = remember {
        HomeViewModel(navController = navController)
    }
    Scaffold(topBar = {
        TopAppBar(title = "Nearby Doctors", navController = navController)
    }) {
        Box(Modifier.padding(it)) {
            NearbyDoctors(homeViewModel = homeViewModel)
        }
    }
}

@Composable
private fun NearbyDoctors(homeViewModel: HomeViewModel) {
    Column(Modifier.fillMaxSize()) {
        LazyColumn() {
            items(listOfDoctor) { doctor ->
                UserCard(doctor = doctor) {
                    homeViewModel.handleNavigationDetail(doctor)
                }
            }
        }
    }
}
