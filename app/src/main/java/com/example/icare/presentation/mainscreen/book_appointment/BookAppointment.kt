package com.example.icare.presentation.mainscreen.book_appointment

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.icare.core.theme.black
import com.example.icare.core.theme.blur
import com.example.icare.core.theme.green500
import com.example.icare.core.theme.neutralWhite
import com.example.icare.core.theme.shapes
import com.example.icare.core.util.Dimens
import PrimaryButton
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.example.icare.R
import com.example.icare.core.theme.gray400


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BookAppointment(navController: NavHostController) {
    val bookAppointmentVieModel = remember {
        BookAppointmentVieModel(navController)
    }
    Scaffold(topBar = { TopAppBar(bookAppointmentVieModel = bookAppointmentVieModel) }) {
        Content()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar(bookAppointmentVieModel: BookAppointmentVieModel) {
    androidx.compose.material3.TopAppBar(title = {
        Text(
            text = "Book Appointment",
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
    }, navigationIcon = {
        IconButton(onClick = { bookAppointmentVieModel.handleBackArrow() }) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }
    })
}

@Composable
private fun Content() {
    val dialogState = remember {
        mutableStateOf(false)
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.mediumPadding, vertical = Dimens.smallPadding),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Date()
        Time()
        Spacer(modifier = Modifier.weight(1f))
        PrimaryButton(text = "Confirm", onClick = { /*TODO*/ dialogState.value = true })
        if (dialogState.value) DialogCongratulation(onClick = {
            dialogState.value = false
        })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Date() {
    val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
    Column() {
        DatePicker(
            colors = DatePickerDefaults.colors(
                containerColor = neutralWhite,
            ),
            state = datePickerState,
            headline = {
                Text(
                    text = "Select Date",
                    style = MaterialTheme.typography.headlineMedium,
                    color = black
                )
            },
            showModeToggle = false
        )
    }
}

@Composable
private fun Time() {
    val timeListSelected =
        remember { mutableStateListOf<Boolean>().apply { repeat(8) { add(false) } } }

    Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = "Select Hour", style = MaterialTheme.typography.headlineMedium)
        TimeGrid(timeListSelected) { index ->
            timeListSelected[index] = !timeListSelected[index]
        }
    }
}


@Composable
private fun BoxOfTime(selected: Boolean, onClick: () -> Unit) {
    val containerColor = if (selected) green500 else blur
    val contentColor = if (selected) neutralWhite else black
    Box(
        Modifier
            .width(100.dp)
            .height(35.dp)
            .clip(shape = shapes.small)
            .background(containerColor)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "10:11",
            style = MaterialTheme.typography.titleMedium, color = contentColor
        )
    }

}

@Composable
private fun TimeGrid(timeList: List<Boolean>, onItemSelected: (Int) -> Unit) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxHeight(2 / 3f),
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(timeList.size) { index ->
            BoxOfTime(selected = timeList[index]) {
                onItemSelected(index)
            }
        }
    }
}

@Composable
private fun DialogCongratulation(onClick: () -> Unit) {
    Dialog(onDismissRequest = { }) {
        Column(
            Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                Modifier
                    .fillMaxWidth(0.9f)
                    .height(IntrinsicSize.Max)
                    .clip(shapes.medium)
                    .background(neutralWhite)
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AsyncImage(
                    model = R.drawable.congratulation,
                    contentDescription = "",
                    modifier = Modifier.size(100.dp)
                )
                Text(text = "Congratulation", style = MaterialTheme.typography.headlineMedium)
                Text(
                    text = "Your appointment with Dr. David Patel\nis confirmed for June 30, 2023, at\n10:00 AM.",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                    color = gray400
                )
                PrimaryButton(text = "Done", onClick = { onClick() })
            }
        }
    }
}