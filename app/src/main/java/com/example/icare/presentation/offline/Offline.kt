package com.example.icare.presentation.offline

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.icare.core.util.Dimens
import com.example.icare.presentation.registeration.component.ImageHeader
import com.example.icare.R
import com.example.icare.core.theme.gray500
import com.example.icare.core.util.HeightSpacer
import com.example.icare.presentation.registeration.component.DescriptionHeader
import com.example.icare.presentation.registeration.component.TextHeader

@Composable
fun OfflineScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.largePadding, vertical = Dimens.mediumPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageHeader(imageRes = R.drawable.no_internet)
        TextHeader(headerString = "No network connection", color = gray500)
        HeightSpacer(
            Dimens
                .largePadding
        )
        TryAgain()
    }
}

@Composable
fun TryAgain() {
    TextButton(onClick = { /*TODO*/ }) {
        Text(text = "Try Again", style = MaterialTheme.typography.titleMedium)
    }
}