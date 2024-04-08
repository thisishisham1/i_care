package com.example.icare.presentation.mainscreen.screens.home.category

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.icare.core.util.Dimens
import com.example.icare.core.util.reusablecomponent.TopAppBar
import com.example.icare.domain.model.listOfPharmacy
import com.example.icare.presentation.mainscreen.screens.home.HomeViewModel
import com.example.icare.presentation.mainscreen.screens.search.tabs.pharmacy.CardPharmacy

@Composable
fun PharmaciesScreen(navController: NavController) {
    val homeViewModel = remember {
        HomeViewModel(navController)
    }
    Scaffold(topBar = {
        TopAppBar(title = "Nearby Pharmacies", navController = navController)
    }) {
        Box(modifier = Modifier.padding(it)) {
            Content(homeViewModel =homeViewModel)
        }
    }
}

@Composable
private fun Content(homeViewModel: HomeViewModel) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.mediumPadding, vertical = Dimens.smallPadding)
    ) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            items(listOfPharmacy) { pharmacy ->
                CardPharmacy(
                    pharmacy = pharmacy,
                    onClickPharmacy = { homeViewModel.handleNavigationDetail(pharmacy) })
            }
        }
    }
}