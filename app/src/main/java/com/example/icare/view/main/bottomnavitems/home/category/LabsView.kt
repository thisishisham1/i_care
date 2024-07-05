package com.example.icare.view.main.bottomnavitems.home.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.icare.MainViewModel
import com.example.icare.core.Dimens
import com.example.icare.core.reusablecomponent.DefaultTopAppBar
import com.example.icare.core.reusablecomponent.ProgressIndicator
import com.example.icare.core.reusablecomponent.UserCard
import com.example.icare.viewmodel.main.bottomnavitems.home.HomeViewModel


@Composable
fun LabsView(navController: NavHostController, vm: MainViewModel) {
    val homeViewModel = remember {
        HomeViewModel(navController)
    }
    Scaffold(topBar = {
        DefaultTopAppBar(title = "Nearby Labs", navController = navController)
    }) {
        Box(modifier = Modifier.padding(it)) {
            Content(homeViewModel = homeViewModel, vm)
        }
    }
}

@Composable
private fun Content(homeViewModel: HomeViewModel, vm: MainViewModel) {
    LaunchedEffect(Unit) {
        vm.fetchLabs()
    }
    val labs by vm.labs.observeAsState()
    val isLoading by vm.isLabsLoading.observeAsState()
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
            LazyColumn(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                labs?.let {
                    items(it) { lab ->
                        UserCard(user = lab) {
                            homeViewModel.handleNavigationDetail(lab.id)
                        }
                    }
                } ?: item { Text("No available labs") }

            }
        }

    }
}