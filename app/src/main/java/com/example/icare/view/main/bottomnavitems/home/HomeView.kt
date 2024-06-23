package com.example.icare.view.main.bottomnavitems.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.icare.R
import com.example.icare.core.Dimens
import com.example.icare.core.theme.black
import com.example.icare.core.theme.green500
import com.example.icare.core.theme.shapes
import com.example.icare.model.classes.users.Doctor
import com.example.icare.model.classes.users.listOfDoctor
import com.example.icare.viewmodel.main.bottomnavitems.home.HomeViewModel


@Composable
fun HomeScreen(navController: NavController) {
    val homeViewModel = remember { HomeViewModel(navController) }
    Scaffold(topBar = { TopBar(homeViewModel = homeViewModel) }) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(
                    top = Dimens.mediumPadding,
                    start = Dimens.mediumPadding,
                    end = Dimens.mediumPadding
                ), verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Categories(homeViewModel)
            NearbyDoctors(homeViewModel)
        }
    }
}

@Composable
private fun TopBar(homeViewModel: HomeViewModel) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(Dimens.smallPadding),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfileImage(imgUrl = "https://img.freepik.com/free-psd/3d-illustration-human-avatar-profile_23-2150671142.jpg?w=740&t=st=1719111511~exp=1719112111~hmac=abe1ed202993adc8d8a2e0b5202d353591681c7a255c729c955c93ec30101509")
        Column {
            Text(text = "Hisham Mohamed", style = MaterialTheme.typography.titleLarge)
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            imageVector = Icons.Default.Notifications,
            contentDescription = "Notification",
            onClick = { homeViewModel.handleNotificationClick() },
        )
        IconButton(
            imageVector = Icons.AutoMirrored.Filled.Chat,
            contentDescription = "Chat",
            onClick = { homeViewModel.handleChatsClick() },
        )
    }
}

@Composable
private fun ProfileImage(imgUrl: String) {

    AsyncImage(
        model = imgUrl,
        contentDescription = "image profile",
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )

}

@Composable
private fun IconButton(onClick: () -> Unit, imageVector: ImageVector, contentDescription: String) {
    androidx.compose.material3.IconButton(onClick = onClick) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            modifier = Modifier.size(24.dp), tint = MaterialTheme.colorScheme.onSurface
        )
    }

}

@Composable
private fun Categories(homeViewModel: HomeViewModel) {
    val categories = listOf(
        Category("Personal Test", R.drawable.personal_cat),
        Category("Chat Bot", R.drawable.chat_bot_cat),
        Category("Labs", R.drawable.lab_equipment_cat),
        Category("Pharmacies", R.drawable.pharmacy_cat)
    )
    Column {
        Text(text = "Category", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = Dimens.mediumPadding)
        ) {
            items(categories) { category ->
                CardCategory(category = category) {
                    homeViewModel.handleClickAction(category.name)
                }
            }
        }
    }
}

data class Category(val name: String, val iconResId: Int)

@Composable
private fun CardCategory(category: Category, onClicked: () -> Unit) {
    val cardColor = MaterialTheme.colorScheme.surfaceVariant
    val shadowColor = cardColor.copy(alpha = 0.1f)
    Box(
        modifier = Modifier
            .size(width = 104.dp, height = 104.dp) // Slightly larger for shadow
            .clip(RoundedCornerShape(12.dp))
    ) {
        // Shadow layer
        Card(
            modifier = Modifier
                .fillMaxSize()
                .offset(x = 2.dp, y = 2.dp),
            colors = CardDefaults.cardColors(containerColor = shadowColor),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 0.dp
            )
        ) {}
        Card(
            modifier = Modifier
                .size(width = 100.dp, height = 100.dp)
                .clip(RoundedCornerShape(12.dp))
                .clickable { onClicked() },
            colors = CardDefaults.cardColors(containerColor = cardColor),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 0.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = category.iconResId,
                    contentDescription = category.name,
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = category.name,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                    color = black
                )
            }
        }
    }
}

@Composable
private fun NearbyDoctors(homeViewModel: HomeViewModel) {
    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = Dimens.smallPadding),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "NearBy Doctors", style = MaterialTheme.typography.headlineMedium)
            Text(text = "See More",
                style = MaterialTheme.typography.titleSmall,
                color = green500,
                modifier = Modifier.clickable {
                    homeViewModel.handleClickSeeMore()
                })
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(contentPadding = PaddingValues(bottom = 16.dp)) {
            items(listOfDoctor) { doctor ->
                UserCard(doctor = doctor) {
                    homeViewModel.handleNavigateDetails(doctor)
                }
            }
        }
    }
}

@Composable
fun UserCard(doctor: Doctor, onClickUser: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
            .clip(shape = shapes.medium)
            .clickable {
                onClickUser()
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(16.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = doctor.imageUrl,
                contentDescription = "Doctor image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                Modifier.weight(1f)
            ) {
                Text(
                    text = doctor.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = doctor.filed,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "location",
                        Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = doctor.address,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star",
                        tint = Color(0xffFEB052),
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = doctor.rating.toString(),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "|",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${doctor.countReviews} Reviews",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}
