package com.example.icare.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.icare.R
import com.example.icare.core.reusablecomponent.DefaultTopAppBar


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TermsAndConditions(navController: NavController) {
    Scaffold(topBar = {
        DefaultTopAppBar(
            title = stringResource(id = R.string.terms_conditions),
            navController = navController
        )
    }) {
        Content()
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
    LazyColumn(Modifier.padding(top = 64.dp, start = 16.dp, end = 16.dp, bottom = 10.dp)) {
        itemsIndexed(listOfCondition) { index, list ->
            if (index == 0) {
                StatementText(text = stringResource(id = R.string.initial_statement))
                Spacer(modifier = Modifier.height(8.dp))
            }
            OneCondition(title = list.first, content = list.second)
            Spacer(modifier = Modifier.height(8.dp))
            if (index == listOfCondition.lastIndex) {
                StatementText(text = stringResource(id = R.string.final_statement))
            }
        }
    }
}

@Composable
fun StatementText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleSmall
    )
}

@Composable
fun OneCondition(title: String, content: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(text = title, style = MaterialTheme.typography.titleMedium)
        Text(text = content, style = MaterialTheme.typography.bodyMedium)
    }
}