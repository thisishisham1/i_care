package com.example.icare.view.main.bottomnavitems.search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.icare.R
import com.example.icare.core.Dimens
import com.example.icare.core.reusablecomponent.ProgressIndicator
import com.example.icare.core.theme.black
import com.example.icare.core.theme.shapes
import com.example.icare.model.classes.Doctor
import com.example.icare.model.classes.Lab
import com.example.icare.model.classes.Pharmacy
import com.example.icare.view.main.bottomnavitems.search.tabs.Doctors.Doctors
import com.example.icare.view.main.bottomnavitems.search.tabs.labs.Labs
import com.example.icare.view.main.bottomnavitems.search.tabs.pharmacy.Pharmacies
import com.example.icare.viewmodel.main.bottomnavitems.search.SearchViewModel


@Composable
fun SearchScreen(navController: NavController) {
    val searchViewModel = remember {
        SearchViewModel(navController)
    }
    val indexTab by searchViewModel.indexTab.collectAsState()
    Scaffold(topBar = {
        SearchBar(searchViewModel)
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = Dimens.mediumPadding)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            RowTabs(selectedTab = indexTab, onClickTab = searchViewModel::onTabChange)
            Spacer(modifier = Modifier.height(16.dp))
            DisplayScreen(indexTab = indexTab, searchViewModel = searchViewModel)
        }
    }

}

@Composable
private fun SearchBar(searchViewModel: SearchViewModel) {
    val searchText by searchViewModel.searchText.collectAsState()
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(25.dp))
                .padding(12.dp), elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ), colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "search icon",
                    Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.width(12.dp))


                BasicTextField(value = searchText,
                    onValueChange = searchViewModel::onSearchTextChange,
                    textStyle = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onSurface
                    ),
                    singleLine = true,
                    modifier = Modifier.weight(1f),
                    decorationBox = { innerTextField ->
                        if (searchText.isEmpty()) {
                            Text(
                                text = stringResource(id = R.string.search_hint),
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                                )
                            )
                        }
                        innerTextField()
                    })
            }
        }
    }

}

@Composable
fun RowTabs(selectedTab: Int, onClickTab: (Int) -> Unit) {
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
    val containerColor: Color =
        if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface // Use surface color for unselected tabs
    val borderColor: Color = if (isSelected) Color.Transparent else black
    Tab(
        selected = selectedTab == index,
        onClick = { onClickTab(index) },
        modifier = Modifier
            .heightIn(45.dp)
            .wrapContentWidth()
            .padding(5.dp)
            .clip(shapes.medium)
            .border(
                border = BorderStroke(
                    color = borderColor, width = if (isSelected) 0.dp else 1.dp
                ), shape = shapes.medium
            )
            .background(containerColor),
        selectedContentColor = MaterialTheme.colorScheme.onPrimary,
        unselectedContentColor = MaterialTheme.colorScheme.onSurface
    ) {
        TabContent(title = title)
    }
}

@Composable
private fun DisplayScreen(indexTab: Int, searchViewModel: SearchViewModel) {
    val filteredList by searchViewModel.filteredList.collectAsState()
    val isLoading by searchViewModel.isLoading.collectAsState()
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ProgressIndicator()
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Searching...", // Informative loading message
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
            }
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
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
    )
}