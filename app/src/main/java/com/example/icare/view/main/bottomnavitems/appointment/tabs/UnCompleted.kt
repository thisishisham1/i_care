package com.example.icare.view.main.bottomnavitems.appointment.tabs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.icare.MainViewModel
import com.example.icare.R
import com.example.icare.core.Dimens
import com.example.icare.core.theme.black
import com.example.icare.core.theme.blur
import com.example.icare.model.classes.apiClass.UsersResponse
import com.example.icare.repository.UsersRepository
import com.example.icare.viewmodel.main.bottomnavitems.appointment.AppointmentViewModel

@Composable
fun UnCompleted(appointmentVieModel: AppointmentViewModel) {
    val vm = MainViewModel(UsersRepository())
    LaunchedEffect(Unit) {
        vm.fetchClinics()
    }
    val clinics by vm.clinics.observeAsState()
    val isLoading by vm.isClinicLoading.observeAsState()

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.mediumPadding, vertical = Dimens.smallPadding)
    ) {
        Date()
        clinics?.let {
            UserCardWithButtons(user = it[0])

        }
    }
}

@Composable
private fun Date() {
    Text(text = "May 22, 2023 - 10.00 AM", style = MaterialTheme.typography.headlineSmall)
}

@Composable
fun UserCardWithButtons(user: UsersResponse) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .clickable { },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = user.img,
                    contentDescription = "${user.img} image",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(Modifier.weight(1f)) {
                    Text(
                        text = "${user.first_name} ${user.last_name}",
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(4.dp))
                    user.specialty?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "location",
                            Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        user.address?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.bodySmall,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
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
                            text = 2.5.toString(),
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
                            text = "${500} Reviews",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
            // Buttons Section
            Spacer(modifier = Modifier.height(16.dp))
            Buttons(onCancelClick = { }, onCompleteClick = { })
        }
    }
}

@Composable
private fun Buttons(onCompleteClick: () -> Unit, onCancelClick: () -> Unit) {
    Row(
        Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = onCancelClick,
            modifier = Modifier
                .width(140.dp)
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = blur, // Replace 'blur' with your actual color
                contentColor = black
            )
        ) {
            Text(
                text = stringResource(id = R.string.appointment_cancel),
                style = MaterialTheme.typography.titleMedium
            )
        }
        Button(
            onClick = onCompleteClick, modifier = Modifier
                .width(140.dp)
                .height(45.dp)
        ) {
            Text(
                text = stringResource(id = R.string.appointment_complete),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
