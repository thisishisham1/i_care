package com.example.icare.view.main.details

import PrimaryButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.icare.R
import com.example.icare.core.Dimens
import com.example.icare.core.reusablecomponent.DefaultTopAppBar
import com.example.icare.core.reusablecomponent.HeightSpacer
import com.example.icare.core.theme.gray400
import com.example.icare.core.theme.gray600
import com.example.icare.core.theme.shapes
import com.example.icare.model.classes.Doctor
import com.example.icare.model.classes.User
import com.example.icare.viewmodel.main.details.DetailsViewModel

@Composable
fun UserDetailsView(userId: Int, userType: String, navController: NavController) {
    val detailsViewModel = remember {
        DetailsViewModel(navController)
    }
    val user by remember {
        mutableStateOf(
            when (userType) {
                "Doctor" -> detailsViewModel.getDoctorDetails(userId)
                "Lab" -> detailsViewModel.getLabDetails(userId)
                "Pharmacy" -> detailsViewModel.getPharmacyDetails(userId)
                else -> null
            }
        )
    }
    Scaffold(topBar = {
        DefaultTopAppBar(title = "$userType Details", navController = navController)
    }) { innerPadding ->
        user?.let {
            Box(modifier = Modifier.padding(innerPadding)) {
                Content(user = it, onClickBookButton = {
                    when (userType) {
                        "Doctor" -> detailsViewModel.handleDoctorBookButtonClick()
                        "Lab" -> detailsViewModel.handleLabBookButtonClick()
                        "Pharmacy" -> detailsViewModel.handlePharmacyBookButtonClick()
                    }
                })
            }
        }

    }
}

@Composable
private fun Content(user: User, onClickBookButton: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = Dimens.mediumPadding, vertical = Dimens.smallPadding),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        UserDetailsCard(user = user)
        Experience()
        AboutMe()
        WorkingHours()
        Spacer(modifier = Modifier.weight(1f))
        PrimaryButton(text = "Book Appointment", onClick = { onClickBookButton() })
        Spacer(modifier = Modifier.height(16.dp)) // Spacing after button
    }
}

@Composable
private fun UserDetailsCard(user: User) {
    Card(
        modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Row(
            modifier = Modifier
                .height(170.dp)
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = user.imageUrl,
                contentDescription = "${user.title} profile",
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.headlineMedium,
                )
                HeightSpacer(8.dp)
                if (user is Doctor) {
                    Text(
                        text = user.specialty,
                        style = MaterialTheme.typography.titleMedium,
                    )
                    HeightSpacer(8.dp)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "location",
                        Modifier.size(18.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = user.address, style = MaterialTheme.typography.titleSmall
                    )
                }
            }
        }
    }

}

@Composable
private fun Experience() {
    val listOfExperience = arrayOf(
        "experience" to R.drawable.medal, "Rating" to R.drawable.star
    )
    val valuesOfExperience = arrayOf(10, 4.5)
    Row {
        listOfExperience.forEachIndexed { index, pair ->
            Column(
                Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    Modifier
                        .size(50.dp)
                        .clip(shapes.large)
                        .background(gray400.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = pair.second),
                        contentDescription = "medal",
                        modifier = Modifier.size(24.dp)
                    )
                }
                Text(
                    text = "${valuesOfExperience[index]}",
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = pair.first,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

            }
        }

    }
}

@Composable
private fun AboutMe() {
    Text(
        text = "About me",
        style = MaterialTheme.typography.headlineSmall,
    )
    Text(
        text = "Dr. David Patel, a dedicated cardiologist, brings a wealth of experience to Golden Gate Cardiology Center in Golden Gate, CA. view more",
        style = MaterialTheme.typography.bodyMedium,
        color = gray600,
    )

}

@Composable
private fun WorkingHours() {
    Text(
        text = "Working Time",
        style = MaterialTheme.typography.headlineSmall,
    )
    Text(
        text = "Monday-Friday, 08.00 AM-18.00 PM",
        style = MaterialTheme.typography.bodyMedium,
        color = gray600,
    )
}