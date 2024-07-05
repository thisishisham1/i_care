package com.example.icare.view.main.bottomnavitems.home

import android.util.Log
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.icare.MainViewModel
import com.example.icare.R
import com.example.icare.core.Dimens
import com.example.icare.core.reusablecomponent.ProgressIndicator
import com.example.icare.core.reusablecomponent.UserCard
import com.example.icare.core.theme.black
import com.example.icare.core.theme.green500
import com.example.icare.repository.UsersRepository
import com.example.icare.viewmodel.main.bottomnavitems.home.HomeViewModel


@Composable
fun HomeScreen(navController: NavController) {
    val mainViewModel = remember {
        MainViewModel(UsersRepository())
    }
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
            NearbyClinics(homeViewModel, mainViewModel)
        }
    }
}

@Composable
private fun TopBar(homeViewModel: HomeViewModel) {
    val user by homeViewModel.user.observeAsState()
    Row(
        Modifier
            .fillMaxWidth()
            .padding(Dimens.smallPadding),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        user?.let {
            ProfileImage(imgUrl = it.img)
            Column {
                Text(text = it.name1, style = MaterialTheme.typography.titleLarge)
            }
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
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.onSurface
        )
    }

}

@Composable
private fun Categories(homeViewModel: HomeViewModel) {
    val categories = listOf(
        Category("Personality Test", R.drawable.personal_cat),
        Category("Chat Bot", R.drawable.chat_bot_cat),
        Category("ECG Scan", R.drawable.electrocardiogram),
        Category("Medical Imaging", R.drawable.xrays),
        Category("Labs", R.drawable.lab_equipment_cat),
        Category("Pharmacies", R.drawable.pharmacy_cat),
    )
    Column {
        Text(text = "Category", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = Dimens.mediumPadding)
        ) {
            itemsIndexed(categories) { _, category ->
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
            .size(width = 114.dp, height = 114.dp) // Slightly larger for shadow
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
                .size(width = 110.dp, height = 110.dp)
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
                    .padding(12.dp),
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
private fun NearbyClinics(homeViewModel: HomeViewModel, mainViewModel: MainViewModel) {
    LaunchedEffect(Unit) {
        mainViewModel.fetchClinics()
    }
    val clinics by mainViewModel.clinics.observeAsState()
    val isLoading by mainViewModel.isClinicLoading.observeAsState()
    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = Dimens.smallPadding),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Nearby Clinics", style = MaterialTheme.typography.headlineMedium)
            Text(text = "See More",
                style = MaterialTheme.typography.titleSmall,
                color = green500,
                modifier = Modifier.clickable {
                    homeViewModel.handleClickSeeMore()
                })
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (isLoading == true) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                ProgressIndicator()
            }
        } else {
            LazyColumn(contentPadding = PaddingValues(bottom = 16.dp)) {
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
                } ?: item {
                    Text("No clinics available")
                }
            }
        }
    }
}