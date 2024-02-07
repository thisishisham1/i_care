package com.example.icare.presentation.onboarding.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.icare.presentation.onboarding.Page
import com.example.icare.core.util.Destinations
import com.example.icare.core.util.Dimens
import com.example.icare.core.theme.green500
import com.example.icare.R
import com.example.icare.data.PreferencesHelper

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page, navController: NavController, preferencesHelper: PreferencesHelper
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .requiredHeight(30.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.skip),
                style = MaterialTheme.typography.titleSmall.copy(fontSize = 15.sp),
                color = green500, modifier = modifier.clickable {
                    preferencesHelper.saveBooleanValue("onBoarding", true)
                    navController.popBackStack()
                    navController.navigate(Destinations.SignIn.route)
                }
            )
            Icon(
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = "",
                tint = green500,
                modifier = Modifier.requiredSize(15.dp)
            )
        }
        Spacer(modifier = modifier.height(Dimens.largePadding))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(page.imgRes).crossfade(true).build(),
                contentDescription = "",
                contentScale = ContentScale.Fit, modifier = Modifier.requiredSize(360.dp)
            )
        }
        Spacer(modifier = modifier.height(Dimens.largePadding))
        Row(
            modifier = modifier
                .padding(Dimens.mediumPadding)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = stringResource(id = page.title),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
        }


    }

}
