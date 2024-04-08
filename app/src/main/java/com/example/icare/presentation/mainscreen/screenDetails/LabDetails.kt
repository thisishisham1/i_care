package com.example.icare.presentation.mainscreen.screenDetails

import PrimaryButton
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.icare.R
import com.example.icare.core.theme.gray400
import com.example.icare.core.theme.gray600
import com.example.icare.core.theme.shapes
import com.example.icare.core.util.Dimens
import com.example.icare.core.util.HeightSpacer
import com.example.icare.domain.model.Lab
import com.example.icare.domain.model.Pharmacy

@Composable
fun LabDetails(labId: Int, navController: NavHostController) {
    val detailsViewModel = remember {
        DetailsViewModel(navController)
    }
    val lab by remember {
        mutableStateOf(detailsViewModel.getLabDetails(labId))

    }
    Scaffold(topBar = {
        TopAppBar(detailsViewModel = detailsViewModel)
    }) {
        Box(modifier = Modifier.padding(it)) {
            Content(
                lab = lab,
                onClickOrderButton = { detailsViewModel.handleLabButton() })
        }
    }
}

@Composable
private fun Content(lab: Lab, onClickOrderButton: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.mediumPadding, vertical = Dimens.smallPadding),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        LabCard(lab = lab)
        Experience()
        WorkingHours()
        Spacer(modifier = Modifier.weight(1f))
        PrimaryButton(text = "Order", onClick = { onClickOrderButton() })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar(detailsViewModel: DetailsViewModel) {
    androidx.compose.material3.TopAppBar(title = {
        Text(
            text = "Lab Details",
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
    }, navigationIcon = {
        IconButton(onClick = { detailsViewModel.handleBackArrow() }) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
        }
    })
}

@Composable
private fun LabCard(lab: Lab) {
    Row(
        modifier = Modifier
            .height(170.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.d),
            contentDescription = "pharmacy profile picture",
            modifier = Modifier
                .size(150.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = lab.name,
                style = MaterialTheme.typography.headlineMedium,
            )
            HeightSpacer(5.dp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "location",
                    Modifier.size(17.dp)
                )
                Text(
                    text = lab.address,
                    style = MaterialTheme.typography.titleSmall
                )
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
                        modifier = Modifier
                            .size(20.dp)
                            .clip(shape = shapes.large)
                    )
                }
                Text(
                    text = "${valuesOfExperience[index]}",
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = pair.first,
                    style = MaterialTheme.typography.titleSmall,
                    color = gray600
                )

            }
        }

    }
}

@Composable
private fun WorkingHours() {
    Text(
        text = "Working Time",
        style = MaterialTheme.typography.headlineMedium,
    )
    Text(
        text = "Monday-Friday, 08.00 AM-18.00 PM",
        style = MaterialTheme.typography.titleMedium,
        color = gray600, modifier = Modifier.padding(start = 4.dp)

    )

}