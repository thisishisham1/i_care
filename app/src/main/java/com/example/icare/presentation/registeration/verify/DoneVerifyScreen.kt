package com.example.icare.presentation.registeration.verify

import PrimaryButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.icare.core.util.Dimens
import com.example.icare.presentation.registeration.component.DescriptionHeader
import com.example.icare.presentation.registeration.component.TextHeader

@Composable
fun DoneVerifyScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.largePadding, vertical = Dimens.mediumPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.largePadding)
    ) {
        CongratulationImage()
        TextHeader(headerString = "Email Verified")
        DescriptionHeader(text = "Congratulations, your email number has been\nverified. You can start using the app")
        Spacer(modifier = Modifier.weight(1f))
        PrimaryButton(text = "Continue", onClick = { /*TODO*/ })
    }
}