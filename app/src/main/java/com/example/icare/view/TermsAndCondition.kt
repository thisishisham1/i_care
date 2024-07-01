package com.example.icare.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.icare.R
import com.example.icare.core.reusablecomponent.DefaultTopAppBar


@Composable
fun TermsAndConditions(navController: NavController) {
    Scaffold(topBar = {
        DefaultTopAppBar(
            title = stringResource(id = R.string.terms_conditions),
            navController = navController
        )
    }) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) { Content() }
    }
}

@Composable
fun Content() {
    val listOfCondition = listOf(
        stringResource(id = R.string.use_license_title) to stringResource(id = R.string.use_license_content),
        stringResource(id = R.string.medical_disclaimer_title) to stringResource(id = R.string.medical_disclaimer_content),
        stringResource(id = R.string.limitations_title) to stringResource(id = R.string.limitations_content),
        stringResource(id = R.string.governing_law_title) to stringResource(id = R.string.governing_law_content),
        stringResource(id = R.string.changes_title) to stringResource(id = R.string.changes_content)
    )
    LazyColumn(
        Modifier
            .padding(16.dp)
            .fillMaxSize(), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            StatementText(
                text = stringResource(id = R.string.initial_statement),
                style = MaterialTheme.typography.headlineSmall
            )
        }
        itemsIndexed(listOfCondition) { index, list ->
            OneCondition(
                title = list.first,
                content = list.second,
                titleStyle = MaterialTheme.typography.titleLarge,
                contentStyle = MaterialTheme.typography.bodyMedium
            )
        }
        item {
            StatementText(
                text = stringResource(id = R.string.final_statement),
                style = MaterialTheme.typography.headlineSmall
            )

        }
    }
}

@Composable
fun StatementText(text: String, style: TextStyle) {
    Text(
        text = text,
        style = style, modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun OneCondition(title: String, content: String, titleStyle: TextStyle, contentStyle: TextStyle) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(text = title, style = titleStyle)
        Text(text = content, style = contentStyle)
    }
}