package com.example.icare.view.main.bottomnavitems.search.tabs.Doctors

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.icare.core.reusablecomponent.HeightSpacer
import com.example.icare.core.reusablecomponent.UserCard
import com.example.icare.model.classes.apiClass.UsersResponse
import com.example.icare.viewmodel.main.bottomnavitems.home.HomeViewModel

@Composable
fun Doctors(homeViewModel: HomeViewModel, clinics: List<UsersResponse>) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${clinics.size} Founds",
            style = MaterialTheme.typography.headlineMedium
        )
    }
    HeightSpacer(5.dp)
    LazyColumn(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        items(clinics) { doctor ->
            UserCard(user = doctor) {
                homeViewModel.handleNavigationDetail(doctor.id)
            }
        }
    }
}
