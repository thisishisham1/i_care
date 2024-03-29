package com.example.icare.presentation.mainscreen.screens.search.tabs.pharmacy

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.icare.R
import com.example.icare.core.theme.gray500
import com.example.icare.core.theme.shapes
import com.example.icare.core.util.HeightSpacer
import com.example.icare.core.util.WidthSpacer
import com.example.icare.domain.model.Pharmacy
import com.example.icare.domain.model.SearchItem

@Composable
fun Pharmacies(onClickPharmacy: (Pharmacy) -> Unit, pharmacies: List<Pharmacy>) {
    Column(Modifier.fillMaxSize()) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${pharmacies.size} Founds",
                style = MaterialTheme.typography.headlineMedium
            )
        }
        HeightSpacer(5.dp)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            items(pharmacies) { pharmacy ->
                CardPharmacy(pharmacy = pharmacy, onClickPharmacy = { onClickPharmacy(pharmacy) })
            }
        }
    }
}

@Composable
private fun CardPharmacy(pharmacy: Pharmacy, onClickPharmacy: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = shapes.small)
            .padding(8.dp)
            .clickable {
                onClickPharmacy()
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            AsyncImage(
                model = R.drawable.d,
                contentDescription = "Doctor image",
                modifier = Modifier
                    .size(140.dp)
                    .clip(shapes.medium),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
            )
            WidthSpacer(16.dp)
            Column(
                Modifier.weight(1f)
            ) {
                Text(
                    text = pharmacy.name,
                    style = MaterialTheme.typography.headlineMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                HeightSpacer(8.dp)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "location",
                        Modifier.size(16.dp), tint = gray500
                    )
                    HeightSpacer(4.dp)
                    Text(
                        text = pharmacy.address,
                        style = MaterialTheme.typography.titleSmall,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                HeightSpacer(4.dp)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star",
                        tint = Color(0xffFEB052), modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = pharmacy.rating.toString(),
                        style = MaterialTheme.typography.titleSmall
                    )
                    WidthSpacer(4.dp)
                    Text(
                        text = "|", style = MaterialTheme.typography.titleSmall,
                        color = gray500
                    )
                    WidthSpacer(4.dp)
                    Text(
                        text = "${pharmacy.countReview} Reviews",
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
        }
    }
}