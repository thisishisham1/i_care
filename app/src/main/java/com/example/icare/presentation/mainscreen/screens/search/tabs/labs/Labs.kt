package com.example.icare.presentation.mainscreen.screens.search.tabs.labs

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.icare.R
import com.example.icare.core.theme.shapes
import com.example.icare.core.util.HeightSpacer
import com.example.icare.core.util.WidthSpacer
import com.example.icare.domain.model.Lab


@Composable
fun Labs(onClickLab: () -> Unit) {

    val listOfLabs = (1..10).map {
        Lab(
            "https://images.unsplash.com/photo-1532187863486-abf9dbad1b69?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "Sunrise Health lab",
            "123 Oak Street, CA 98765",
            4.6,
            123.0
        )
    }
    Column(Modifier.fillMaxSize()) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${listOfLabs.size} Founds",
                style = MaterialTheme.typography.headlineMedium
            )
        }
        HeightSpacer(5.dp)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            items(listOfLabs) { lab ->
                CardDoctor(lab = lab) {
                    onClickLab()
                }
            }
        }
    }
}

@Composable
private fun CardDoctor(lab: Lab, onClickDoctor: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clip(shape = shapes.small)
            .padding(5.dp)
            .clickable { onClickDoctor() },
        shadowElevation = 2.dp
    ) {
        Box(
            Modifier
                .fillMaxSize()
        ) {
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(5.dp)
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
                        text = lab.name,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "location",
                            Modifier.size(17.dp)
                        )
                        Text(
                            text = lab.address,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Star", tint = Color(0xffFEB052),
                            modifier = Modifier.size(17.dp)
                        )
                        Text(
                            text = lab.rating.toString(),
                            style = MaterialTheme.typography.titleSmall
                        )
                        WidthSpacer(2.dp)
                        Text(text = "|")
                        WidthSpacer(2.dp)
                        Text(
                            text = "${lab.reviewCount} Reviews",
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }
            }
        }
    }
}