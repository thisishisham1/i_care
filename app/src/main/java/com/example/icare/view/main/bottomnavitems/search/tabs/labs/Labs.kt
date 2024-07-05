package com.example.icare.view.main.bottomnavitems.search.tabs.labs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.icare.core.reusablecomponent.HeightSpacer
import com.example.icare.core.reusablecomponent.UserCard
import com.example.icare.model.classes.UsersJson


@Composable
fun Labs(onClickLab: (UsersJson) -> Unit, labs: List<UsersJson>) {

    Column(Modifier.fillMaxSize()) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${labs?.size ?: "null"} Founds",
                style = MaterialTheme.typography.headlineMedium
            )
        }
        HeightSpacer(5.dp)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            items(labs) { lab ->
                UserCard(
                    user = lab
                ) {
                }
            }
        }

    }

}
