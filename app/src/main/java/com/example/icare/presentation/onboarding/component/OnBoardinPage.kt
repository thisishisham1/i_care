package com.example.icare.presentation.onboarding.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.icare.presentation.onboarding.Page
import com.example.icare.presentation.onboarding.pages
import com.example.icare.ui.theme.ICareTheme
import com.example.icare.ui.theme.primaryGreen

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(), horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "skip",
                style = MaterialTheme.typography.labelLarge, color = primaryGreen,
                modifier = modifier.clickable { }
            )
        }
        Spacer(modifier = modifier.height(25.dp))
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f),
                painter = painterResource(id = page.imgRes),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }
        Spacer(modifier = modifier.height(25.dp))
        Row {
            Text(
                text = page.title,
                style = MaterialTheme.typography.headlineSmall
            )
        }


    }

}

