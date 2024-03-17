package com.example.icare.presentation.mainscreen.screenDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.icare.core.theme.green200
import com.example.icare.domain.model.Doctor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorDetails(doctorId: Int) {
    val detailsViewModel = remember {
        DetailsViewModel()
    }
    val doctor = detailsViewModel.getDoctorDetails(doctorId)
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Doctor Details") }, navigationIcon = {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                Modifier.clickable { })
        })
    }) {
        Box(modifier = Modifier.padding(it)) {
            Column(
                Modifier
                    .fillMaxSize()
                    .background(green200)
            ) {
                Text(text = "${doctor.id}")
                Text(text = doctor.name)
            }
        }
    }
}