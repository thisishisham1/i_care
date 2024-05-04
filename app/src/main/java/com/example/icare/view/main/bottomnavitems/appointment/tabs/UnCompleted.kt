package com.example.icare.view.main.bottomnavitems.appointment.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.icare.R
import com.example.icare.core.Dimens
import com.example.icare.core.reusablecomponent.WidthSpacer
import com.example.icare.core.theme.black
import com.example.icare.core.theme.blur
import com.example.icare.core.theme.shapes
import com.example.icare.model.classes.Doctor
import com.example.icare.model.classes.listOfDoctor
import com.example.icare.viewmodel.main.bottomnavitems.appointment.AppointmentViewModel

@Composable
fun UnCompleted(appointmentVieModel: AppointmentViewModel) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.mediumPadding, vertical = Dimens.smallPadding)
    ) {
        Date()
        DoctorCard(doctor = listOfDoctor[0], appointmentVieModel = appointmentVieModel)
    }
}

@Composable
private fun Date() {
    Text(text = "May 22, 2023 - 10.00 AM", style = MaterialTheme.typography.headlineMedium)
}

@Composable
private fun DoctorCard(
    doctor: Doctor,
    appointmentVieModel: AppointmentViewModel
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = shapes.medium)
            .padding(5.dp)
            .clickable {
                appointmentVieModel.handleDoctorClick()
            },
        shadowElevation = 2.dp
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(5.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
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
            Buttons(
                onCancelClick = { appointmentVieModel.handleCancelClick() },
                onCompleteClick = { appointmentVieModel.handleCompleteClick() }
            )
        }

    }

}

@Composable
private fun Buttons(onCompleteClick: () -> Unit, onCancelClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = { onCancelClick() },
            Modifier
                .width(140.dp)
                .height(45.dp), colors = ButtonDefaults.buttonColors(
                containerColor = blur, contentColor = black
            )
        ) {
            Text(
                text = stringResource(id = R.string.appointment_cancel),
                style = MaterialTheme.typography.titleMedium
            )
        }
        Button(
            onClick = { onCompleteClick() },
            Modifier
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
