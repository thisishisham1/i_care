package com.example.icare.presentation.mainscreen.screens.appointment

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.icare.core.theme.black
import com.example.icare.core.theme.green500
import com.example.icare.core.theme.neutralWhite
import com.example.icare.core.theme.shapes
import com.example.icare.presentation.mainscreen.screens.appointment.tabs.Canceled
import com.example.icare.presentation.mainscreen.screens.appointment.tabs.Completed
import com.example.icare.presentation.mainscreen.screens.appointment.tabs.UnCompleted
import com.example.icare.R

@Composable
fun AppointmentScreen() {
    val appointmentViewModel = remember {
        AppointmentViewModel()
    }
    val indexTab by appointmentViewModel.indexTab.collectAsState()
    Column(
        Modifier
            .fillMaxSize()
    ) {
        RowTabs(onClickTab = appointmentViewModel::onChangeTab, selectedTab = indexTab)
        Content(selectedTab = indexTab, appointmentViewModel)
    }
}

@Composable
private fun RowTabs(onClickTab: (Int) -> Unit, selectedTab: Int) {
    val tabs = listOf(
        stringResource(id = R.string.appointment_tab_uncompleted),
        stringResource(id = R.string.appointment_tab_completed),
        stringResource(id = R.string.appointment_tab_canceled)
    )
    TabRow(selectedTabIndex = selectedTab, divider = {}, indicator = {}) {
        tabs.forEachIndexed { index, title ->
            val isSelected: Boolean = selectedTab == index
            val borderColor: Color = if (isSelected) Color.Transparent else black
            val containerColor: Color = if (selectedTab == index) green500 else neutralWhite
            Tab(
                selected = selectedTab == index,
                onClick = { onClickTab(index) },
                selectedContentColor = neutralWhite,
                unselectedContentColor = black,
                modifier = Modifier
                    .height(45.dp)
                    .padding(5.dp)
                    .clip(
                        shapes.medium
                    )
                    .border(
                        border = BorderStroke(
                            color = borderColor,
                            width = if (isSelected) 0.dp else 1.dp
                        ),
                        shape = shapes.medium
                    )
                    .background(containerColor)
            ) {
                TabTitle(title = title)
            }
        }
    }
}

@Composable
private fun TabTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
private fun Content(selectedTab: Int, appointmentViewModel: AppointmentViewModel) {
    when (selectedTab) {
        0 -> UnCompleted(appointmentViewModel)
        1 -> Completed()
        else -> Canceled()
    }
}