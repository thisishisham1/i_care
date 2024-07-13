package com.example.icare.view.main.bottomnavitems.home.category

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.icare.MainViewModel
import com.example.icare.core.Dimens
import com.example.icare.core.reusablecomponent.DefaultTopAppBar
import com.example.icare.core.reusablecomponent.ProgressIndicator
import com.example.icare.core.reusablecomponent.UserCard
import com.example.icare.repository.UsersRepository
import com.example.icare.viewmodel.main.bottomnavitems.home.HomeViewModel


@Composable
fun PharmaciesView(navController: NavController) {
    val homeViewModel = remember {
        HomeViewModel(navController)
    }
    Scaffold(topBar = {
        DefaultTopAppBar(title = "Nearby Pharmacies", navController = navController)
    }) {
        Box(modifier = Modifier.padding(it)) {
            Content(homeViewModel = homeViewModel)
        }
    }
}

@Composable
private fun Content(homeViewModel: HomeViewModel) {
    val vm = remember { MainViewModel(UsersRepository()) }
    LaunchedEffect(Unit) {
        vm.fetchPharmacies()
    }
    val pharmacy by vm.pharmacies.observeAsState()
    val isLoading by vm.isPharmacyLoading.observeAsState()
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.mediumPadding, vertical = Dimens.smallPadding)
    ) {
        if (isLoading == true) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                ProgressIndicator()
            }
        } else {
            pharmacy?.let {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    items(it) { pharmacy ->
                        UserCard(user = pharmacy) {
                            homeViewModel.handleNavigationDetail(pharmacy.id)
                        }
                    }
                }
            } ?: "No pharmacy available"
        }
    }
}