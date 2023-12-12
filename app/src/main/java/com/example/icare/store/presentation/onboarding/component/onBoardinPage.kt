package com.example.icare.store.presentation.onboarding.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.icare.store.presentation.onboarding.Page
import com.example.icare.store.util.Destinations
import com.example.icare.store.util.Dimens
import com.example.icare.ui.theme.primaryGreen

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page, navController: NavController
) {
    Column(
        modifier = modifier
    ) {

        Box(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(page.imgRes).crossfade(true).build(),
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(Dimens.mediumPadding),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Skip",
                    style = MaterialTheme.typography.labelLarge,
                    color = primaryGreen, modifier = modifier.clickable {
                        navController.popBackStack()
                        navController.navigate(Destinations.HomeScreen.route)
                    }
                )
            }
        }
        Spacer(modifier = modifier.height(Dimens.smallPadding))
        Row(modifier = modifier.padding(Dimens.mediumPadding)) {
            Text(
                text = page.title,
                style = MaterialTheme.typography.headlineSmall
            )
        }


    }

}
