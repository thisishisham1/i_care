package com.example.icare.view.main.bottomnavitems.home

import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.icare.R
import com.example.icare.core.Dimens
import com.example.icare.core.reusablecomponent.HeightSpacer
import com.example.icare.core.reusablecomponent.WidthSpacer
import com.example.icare.core.theme.gray500
import com.example.icare.core.theme.gray600
import com.example.icare.core.theme.shapes
import com.example.icare.model.classes.users.Doctor
import com.example.icare.model.classes.users.listOfDoctor
import com.example.icare.viewmodel.main.bottomnavitems.home.HomeViewModel


@Composable
fun HomeScreen(navController: NavController) {
    val homeViewModel = remember { HomeViewModel(navController) }
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.mediumPadding, vertical = Dimens.smallPadding),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        TopBar(homeViewModel = homeViewModel)
        Categories(homeViewModel)
        NearbyDoctors(homeViewModel)
    }
}

@Composable
private fun TopBar(homeViewModel: HomeViewModel) {
    Row(
        Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfileImage(model = R.drawable.profile_image)
        Text(text = "Hisham Mohamed", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            icon = R.drawable.notification,
            label = "Notification",
            onClicked = { homeViewModel.handleNotificationClick() },
        )
        IconButton(
            icon = R.drawable.chat,
            label = "Chat",
            onClicked = { homeViewModel.handleChatsClick() },
        )
    }
}

@Composable
private fun ProfileImage(model: Int) {
    Box(
        Modifier
            .clip(CircleShape)
    ) {
        AsyncImage(
            model = model,
            contentDescription = "image profile",
            modifier = Modifier.size(40.dp)
        )
    }
}

@Composable
private fun IconButton(onClicked: () -> Unit, icon: Int, label: String) {
    Icon(
        painter = painterResource(id = icon),
        contentDescription = label,
        modifier = Modifier
            .size(25.dp)
            .clickable {
                onClicked()
            }
    )
}

@Composable
private fun Categories(homeViewModel: HomeViewModel) {
    val listOfCategory = listOf(
        "Personal\ntest" to R.drawable.personal_cat,
        "Chat Bot" to R.drawable.chat_bot_cat,
        "Labs" to R.drawable.lab_equipment_cat,
        "Pharmacies" to R.drawable.pharmacy_cat
    )
    Column {
        Text(text = "Category", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            items(listOfCategory) { category ->
                CardCategory(category = category) {
                    homeViewModel.handleClickAction(category.first)
                }
            }
        }
    }
}

@Composable
private fun CardCategory(category: Pair<String, Int>, onClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
            .clickable { onClicked() }, contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = category.second,
                contentDescription = category.first
            )
            Text(
                text = category.first,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall.copy(fontSize = 13.sp)
            )
        }
    }

}

@Composable
private fun NearbyDoctors(homeViewModel: HomeViewModel) {
    Column(Modifier.fillMaxSize()) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "NearBy Doctors", style = MaterialTheme.typography.headlineMedium)
            Text(
                text = "See More",
                style = MaterialTheme.typography.titleSmall,
                color = gray600,
                modifier = Modifier.clickable {
                    homeViewModel.handleClickSeeMore()
                    //todo:handle see more click
                }
            )
        }
        LazyColumn() {
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
            .clip(shape = shapes.small)
            .padding(8.dp)
            .clickable {
                onClickUser()
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(12.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = R.drawable.d,
                contentDescription = "Doctor image",
                modifier = Modifier
                    .size(140.dp)
                    .clip(shapes.medium),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
            )
            WidthSpacer(16.dp)
            Column(
                Modifier.weight(1f)
            ) {
                Text(
                    text = doctor.name,
                    style = MaterialTheme.typography.headlineMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(text = doctor.filed, style = MaterialTheme.typography.titleMedium)
                HeightSpacer(8.dp)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "location",
                        Modifier.size(16.dp), tint = gray500
                    )
                    HeightSpacer(4.dp)
                    Text(
                        text = doctor.address,
                        style = MaterialTheme.typography.titleSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                HeightSpacer(4.dp)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star",
                        tint = Color(0xffFEB052), modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = doctor.rating.toString(),
                        style = MaterialTheme.typography.titleSmall
                    )
                    WidthSpacer(4.dp)
                    Text(
                        text = "|", style = MaterialTheme.typography.titleSmall,
                        color = gray500
                    )
                    WidthSpacer(4.dp)
                    Text(
                        text = "${doctor.countReviews} Reviews",
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
        }
    }
}
