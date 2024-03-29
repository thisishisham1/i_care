package com.example.icare.presentation.mainscreen.screens.search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.icare.core.theme.black
import com.example.icare.core.theme.green500
import com.example.icare.core.theme.neutralWhite
import com.example.icare.core.theme.shapes
import com.example.icare.R
import com.example.icare.core.theme.gray400
import com.example.icare.core.theme.green700
import com.example.icare.core.util.Dimens
import com.example.icare.domain.model.Doctor
import com.example.icare.domain.model.Lab
import com.example.icare.domain.model.Pharmacy
import com.example.icare.presentation.mainscreen.screens.search.tabs.Doctors.Doctors
import com.example.icare.presentation.mainscreen.screens.search.tabs.labs.Labs
import com.example.icare.presentation.mainscreen.screens.search.tabs.pharmacy.Pharmacies

@Composable
fun SearchScreen(navController: NavController) {
    val searchViewModel = remember {
        SearchViewModel(navController)
    }
    val indexTab by searchViewModel.indexTab.collectAsState()
    Scaffold(topBar = {
        SearchBar(searchViewModel)
    }) {
        Box(
            modifier = Modifier
                .padding(it)
        ) {
            Column(
                Modifier
                    .padding(
                        horizontal = Dimens.mediumPadding,
                        vertical = Dimens.smallPadding
                    )
            ) {
                //1- Row tabs
                RowTabs(selectedTab = indexTab, onClickTab = searchViewModel::onTabChange)
                DisplayScreen(indexTab = indexTab, searchViewModel = searchViewModel)
            }
        }
    }

}

@Composable
private fun SearchBar(searchViewModel: SearchViewModel) {
    val searchText by searchViewModel.searchText.collectAsState()
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(0.9f),
            value = searchText,
            onValueChange = searchViewModel::onSearchTextChange,
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
                // TODO: Add logic to trigger search action
            }),
            placeholder = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "search icon", Modifier.size(17.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.search_hint),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        )
    }
}

@Composable
private fun RowTabs(selectedTab: Int, onClickTab: (Int) -> Unit) {
    val tabs = listOf(
        stringResource(id = R.string.search_tab_doctors),
        stringResource(id = R.string.search_tab_pharmacies),
        stringResource(id = R.string.search_tab_labs)
    )
    TabRow(selectedTabIndex = selectedTab, divider = {}, indicator = {}) {
        tabs.forEachIndexed { index, title ->
            DefaultTab(index = index, selectedTab = selectedTab, title = title) {
                onClickTab(index)
            }
        }
    }
}

@Composable
private fun DefaultTab(index: Int, selectedTab: Int, title: String, onClickTab: (Int) -> Unit) {
    val isSelected: Boolean = selectedTab == index
    val borderColor: Color = if (isSelected) Color.Transparent else black
    val containerColor: Color = if (selectedTab == index) green500 else neutralWhite
    Tab(
        selected = selectedTab == index,
        onClick = { onClickTab(index) },
        modifier = Modifier
            .heightIn(45.dp)
            .padding(5.dp)
            .clip(shapes.medium)
            .border(
                border = BorderStroke(
                    color = borderColor,
                    width = if (isSelected) 0.dp else 1.dp
                ),
                shape = shapes.medium
            )
            .background(containerColor),
        selectedContentColor = neutralWhite,
        unselectedContentColor = black
    ) {
        TabContent(title = title)
    }
}

@Composable
private fun DisplayScreen(indexTab: Int, searchViewModel: SearchViewModel) {
    val filteredList by searchViewModel.filteredList.collectAsState()
    val isLoading by searchViewModel.isLoading.collectAsState()
    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = green700, strokeWidth = 3.dp)
        }
    } else {
        when (indexTab) {
            0 -> Doctors(
                doctors = filteredList.filterIsInstance<Doctor>(),
                onClickDoctor = searchViewModel::handleClickItem,
            )

            1 -> Pharmacies(
                pharmacies = filteredList.filterIsInstance<Pharmacy>(),
                onClickPharmacy = searchViewModel::handleClickItem
            )

            else -> Labs(
                labs = filteredList.filterIsInstance<Lab>(),
                onClickLab = searchViewModel::handleClickItem
            )
        }

    }
}

@Composable
private fun TabContent(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium
    )
}