package com.example.icare.view.main.bottomnavitems.home.category

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
import com.example.icare.core.reusablecomponent.DefaultTopAppBar
import com.example.icare.model.classes.users.listOfDoctor
import com.example.icare.view.main.bottomnavitems.home.UserCard
import com.example.icare.viewmodel.main.bottomnavitems.home.HomeViewModel


@Composable
fun DoctorsView(navController: NavController) {
    val homeViewModel = remember {
        HomeViewModel(navController = navController)
    }
    Scaffold(topBar = {
        DefaultTopAppBar(title = "Nearby Doctors", navController = navController)
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
