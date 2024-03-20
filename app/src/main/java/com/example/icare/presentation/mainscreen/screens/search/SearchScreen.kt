package com.example.icare.presentation.mainscreen.screens.search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.icare.core.theme.black
import com.example.icare.core.theme.gray400
import com.example.icare.core.theme.green500
import com.example.icare.core.theme.neutralWhite
import com.example.icare.core.theme.shapes
import com.example.icare.core.util.Dimens
import com.example.icare.presentation.mainscreen.screens.search.tabs.Doctors.Doctors
import com.example.icare.presentation.mainscreen.screens.search.tabs.labs.Labs
import com.example.icare.presentation.mainscreen.screens.search.tabs.pharmacy.Pharmacies

@Composable
fun SearchScreen(navController: NavController) {
    val searchViewModel = remember {
        SearchViewModel(navController)
    }
    var selectedTab by remember {
        mutableIntStateOf(0)
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.mediumPadding, vertical = Dimens.smallPadding),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Search(searchViewModel)
        RowTabs(selectedTab) {
            selectedTab = it
        }
        Content(selectedTab = selectedTab, searchViewModel)
    }
}

@Composable
private fun Search(searchViewModel: SearchViewModel) {
    Row(
        Modifier
            .fillMaxWidth(), horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(0.9f),
            value = searchViewModel.valueState.value,
            onValueChange = { newValue -> searchViewModel.onValueChange(newValue) },
            textStyle = MaterialTheme.typography.titleMedium,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = gray400.copy(alpha = 0.4f),
                unfocusedContainerColor = gray400.copy(alpha = 0.4f),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = shapes.medium,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                // TODO: handle button Search
            }),
            placeholder = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "search icon", Modifier.size(17.dp)
                    )
                    Text(text = "Search..", style = MaterialTheme.typography.titleMedium)
                }
            },
        )
    }

}

@Composable
private fun RowTabs(selectedTab: Int, onClickTab: (Int) -> Unit) {
    val tabs = listOf("Doctors", "pharmacies", "Labs")
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
                    .width(intrinsicSize = IntrinsicSize.Min)
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
                TabContent(title = title)
            }
        }
    }
}

@Composable
private fun Content(selectedTab: Int, searchViewModel: SearchViewModel) {
    when (selectedTab) {
        0 -> Doctors(searchViewModel = searchViewModel)
        1 -> Pharmacies(searchViewModel=searchViewModel)
        else -> Labs(searchViewModel=searchViewModel)
    }
}

@Composable
private fun TabContent(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium
    )
}
