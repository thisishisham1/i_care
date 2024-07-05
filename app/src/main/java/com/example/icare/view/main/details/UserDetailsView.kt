package com.example.icare.view.main.details

import PrimaryButton
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.icare.MainViewModel
import com.example.icare.R
import com.example.icare.core.Dimens
import com.example.icare.core.reusablecomponent.DefaultTopAppBar
import com.example.icare.core.reusablecomponent.HeightSpacer
import com.example.icare.core.reusablecomponent.ProgressIndicator
import com.example.icare.core.theme.gray400
import com.example.icare.core.theme.gray600
import com.example.icare.core.theme.shapes
import com.example.icare.model.classes.apiClass.UsersResponse
import com.example.icare.viewmodel.main.details.DetailsViewModel

@Composable
fun UserDetailsView(userId: Int, navController: NavController, mainViewModel: MainViewModel) {
    val detailsViewModel = remember {
        DetailsViewModel(navController, mainViewModel = mainViewModel)
    }
    LaunchedEffect(key1 = userId) {
        detailsViewModel.fetchUserDetails(userId)
    }
    val userDetails by detailsViewModel.user.collectAsState()
    val isLoading by detailsViewModel.isLoading.collectAsState()
    Log.d("UserDetailsView", "Composed with userId: $userId") // Log when composable is launched
    Log.d("UserDetailsView", "Initial isLoading state: $isLoading")

    Scaffold(topBar = {
        DefaultTopAppBar(title = "${userDetails?.category} Details", navController = navController)
    }) { innerPadding ->
        if (isLoading) {
            Log.d("UserDetailsView", "Loading user details...")
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                ProgressIndicator()
            }
        } else {
            userDetails?.let { safeUser ->
                Log.d("UserDetailsView", "User details loaded: $safeUser")
                Box(modifier = Modifier.padding(innerPadding)) {
                    Content(user = safeUser, onClickBookButton = {
                        try {
                            detailsViewModel.handleBookButtonClick(safeUser)
                        } catch (e: Exception) {
                            Log.e(
                                "UserDetailsView",
                                "Error handling book button click: ${e.message}"
                            )
                        }
                    })
                }
            } ?: run {
                Log.d(
                    "UserDetailsView",
                    "User details not found for userId: $userId"
                ) // Log if user is null
                Text("User details not found.")
            }
        }
    }
}

@Composable
private fun Content(user: UsersResponse, onClickBookButton: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = Dimens.mediumPadding, vertical = Dimens.smallPadding),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        UserDetailsCard(user = user)
        Experience(user)
        AboutMe(user)
        WorkingHours(user)
        Spacer(modifier = Modifier.weight(1f))
        PrimaryButton(text = "Book Appointment", onClick = { onClickBookButton() })
        Spacer(modifier = Modifier.height(16.dp)) // Spacing after button
    }
}

@Composable
private fun UserDetailsCard(user: UsersResponse) {
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
                model = user.img ?: user.effectiveImg,
                contentDescription = "${user.img} profile",
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
                    text = "${user.first_name} ${user.last_name}",
                    style = MaterialTheme.typography.headlineMedium,
                )
                HeightSpacer(8.dp)
                user.specialty?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
                HeightSpacer(8.dp)

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "location",
                        Modifier.size(18.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    user.address?.let {
                        Text(
                            text = it, style = MaterialTheme.typography.titleSmall
                        )
                    }
                }
            }
        }
    }

}

@Composable
private fun Experience(user: UsersResponse) {
    val listOfExperience = arrayOf(
        "experience" to R.drawable.medal, "Rating" to R.drawable.star
    )
    Row {
        listOfExperience.forEachIndexed { index, pair ->
            Column(
                Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally
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
                    text = if (pair.first == "experience") user.experience.toString() else 2.5.toString(),
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
private fun AboutMe(user: UsersResponse) {
    Text(
        text = "About me",
        style = MaterialTheme.typography.headlineSmall,
    )
    user.description?.let {
        Text(
            text = it,
            style = MaterialTheme.typography.bodyMedium,
            color = gray600,
        )
    }

}

@Composable
private fun WorkingHours(user: UsersResponse) {
    Text(
        text = "Working Time",
        style = MaterialTheme.typography.headlineSmall,
    )
    user.work_time?.let {
        Text(
            text = it,
            style = MaterialTheme.typography.bodyMedium,
            color = gray600,
        )
    }
}