package com.example.icare.view.main.bottomnavitems.home.category

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.icare.MainViewModel
import com.example.icare.core.reusablecomponent.DefaultTopAppBar
import com.example.icare.core.reusablecomponent.ProgressIndicator
import com.example.icare.core.reusablecomponent.UserCard
import com.example.icare.repository.UsersRepository
import com.example.icare.viewmodel.main.bottomnavitems.home.HomeViewModel


@Composable
fun DoctorsView(navController: NavController) {
    val vm = remember { MainViewModel(UsersRepository()) }

    val homeViewModel = remember {
        HomeViewModel(navController = navController)
    }
    Scaffold(topBar = {
        DefaultTopAppBar(title = "Nearby Doctors", navController = navController)
    }) {
        Box(Modifier.padding(it)) {
            NearbyDoctors(homeViewModel = homeViewModel, vm)
        }
    }
}

@Composable
private fun NearbyDoctors(homeViewModel: HomeViewModel, vm: MainViewModel) {
    LaunchedEffect(Unit) {
        vm.fetchClinics()
    }
    val clinics by vm.clinics.observeAsState()
    val isLoading by vm.isClinicLoading.observeAsState()
    Column(Modifier.fillMaxSize()) {
        if (isLoading == true) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                ProgressIndicator()
            }
        } else {
            LazyColumn() {
                clinics?.let {
                    items(it) { clinic ->
                        UserCard(user = clinic) {
                            try {
                                homeViewModel.handleNavigationDetail(clinic.id)
                            } catch (e: Exception) {
                                Log.e("NearbyClinics", "Error navigating to details: ${e.message}")
                            }
                        }
                    }
                }
            }
        }
    }
}
