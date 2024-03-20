package com.example.icare.presentation.mainscreen.screens.search.tabs.pharmacy

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.icare.R
import com.example.icare.core.theme.shapes
import com.example.icare.core.util.HeightSpacer
import com.example.icare.core.util.WidthSpacer
import com.example.icare.domain.model.Pharmacy
import com.example.icare.domain.model.listOfPharmacy
import com.example.icare.presentation.mainscreen.screens.search.SearchViewModel

@Composable
fun Pharmacies(searchViewModel: SearchViewModel) {
    Column(Modifier.fillMaxSize()) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${listOfPharmacy.size} Founds",
                style = MaterialTheme.typography.headlineMedium
            )
        }
        HeightSpacer(5.dp)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            items(listOfPharmacy) { pharmacy ->
                CardPharmacy(pharmacy = pharmacy) {
                    searchViewModel.handleClickPharmacy(pharmacy)
                }
            }
        }
    }
}

@Composable
private fun CardPharmacy(pharmacy: Pharmacy, onClickPharmacy: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clip(shape = shapes.small)
            .padding(5.dp)
            .clickable { onClickPharmacy() },
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
                        text = pharmacy.name,
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
                            text = pharmacy.address,
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
                            text = pharmacy.rating.toString(),
                            style = MaterialTheme.typography.titleSmall
                        )
                        WidthSpacer(2.dp)
                        Text(text = "|")
                        WidthSpacer(2.dp)
                        Text(
                            text = "${pharmacy.reviewCount} Reviews",
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }
            }
        }
    }
}