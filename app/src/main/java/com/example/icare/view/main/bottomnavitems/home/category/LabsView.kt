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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.icare.core.util.Dimens
import com.example.icare.core.util.reusablecomponent.DefaultTopAppBar
import com.example.icare.model.classes.listOfLabs
import com.example.icare.view.main.bottomnavitems.search.tabs.labs.CardDoctor
import com.example.icare.viewmodel.main.bottomnavitems.home.HomeViewModel


@Composable
fun LabsView(navController: NavHostController) {
    val homeViewModel = remember {
        HomeViewModel(navController)
    }
    Scaffold(topBar = {
        DefaultTopAppBar(title = "Nearby Labs", navController = navController)
    }) {
        Box(modifier = Modifier.padding(it)) {
            Content(homeViewModel = homeViewModel)
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
            items(listOfLabs) { lab ->
                CardDoctor(lab = lab) {
                    homeViewModel.handleNavigationDetail(lab)
                }
            }
        }
    }
}