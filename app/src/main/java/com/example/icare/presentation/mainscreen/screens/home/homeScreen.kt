package com.example.icare.presentation.mainscreen.screens.home

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.icare.R
import com.example.icare.core.theme.gray600
import com.example.icare.core.theme.shapes
import com.example.icare.core.util.Dimens
import com.example.icare.core.util.WidthSpacer
import com.example.icare.domain.model.Doctor
import com.example.icare.domain.model.listOfDoctor


@Composable
fun HomeScreen(navController: NavController) {
    val homeViewModel = remember {
        HomeViewModel(navController)
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.mediumPadding, vertical = Dimens.smallPadding),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        TopBar({}, {})
        Categories({})
        NearbyDoctors(homeViewModel)
    }
}

@Composable
private fun TopBar(onClickedNotification: () -> Unit, onClickedChat: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfileImage(model = R.drawable.profile_image)
        Text(text = "Hisham Mohamed", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.weight(1f))
        NotificationIcon(onClickedNotification)
        ChatIcon(onClickedChat)
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
private fun NotificationIcon(onClicked: () -> Unit) {
    Icon(
        painter = painterResource(id = R.drawable.notification),
        contentDescription = "Notification",
        modifier = Modifier
            .size(25.dp)
            .clickable {
                onClicked()
            }
    )
}

@Composable
private fun ChatIcon(onClicked: () -> Unit) {
    Icon(
        painter = painterResource(id = R.drawable.chat),
        contentDescription = "Chats",
        modifier = Modifier
            .size(25.dp)
            .clickable {
                onClicked()
            }
    )
}

@Composable
private fun Categories(onClicked: () -> Unit) {
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
                    onClicked()
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
                modifier = Modifier.clickable { //todo:handle see more click
                }
            )
        }
        LazyColumn(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            items(listOfDoctor) { doctor ->
                DoctorCard(doctor = doctor) {
                    homeViewModel.handleNavigateDetails(doctor)
                }
            }
        }
    }
}

@Composable
private fun DoctorCard(doctor: Doctor, onDoctorClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clip(shape = shapes.small)
            .padding(5.dp)
            .clickable {
                onDoctorClick()
            },
        shadowElevation = 2.dp
    ) {
        Box(
            Modifier
                .fillMaxSize()
        ) {
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(5.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.d),
                    contentDescription = "Doctor image"
                )
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(5.dp), verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        text = "DR ${doctor.name}",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center
                    )
                    Text(text = doctor.filed, style = MaterialTheme.typography.titleSmall)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "location",
                            Modifier.size(17.dp)
                        )
                        Text(
                            text = doctor.address,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Star",
                            tint = Color(0xffFEB052), modifier = Modifier.size(17.dp)
                        )
                        Text(
                            text = doctor.rating.toString(),
                            style = MaterialTheme.typography.titleSmall
                        )
                        WidthSpacer(2.dp)
                        Text(text = "|")
                        WidthSpacer(2.dp)
                        Text(
                            text = "${doctor.countReviews} Reviews",
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }
            }
        }
    }
}
