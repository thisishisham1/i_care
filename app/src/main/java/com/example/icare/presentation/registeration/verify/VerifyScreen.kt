package com.example.icare.presentation.registeration.verify

import PrimaryButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.icare.R
import com.example.icare.core.util.Dimens
import com.example.icare.presentation.registeration.component.DescriptionHeader
import com.example.icare.presentation.registeration.component.ImageHeader
import com.example.icare.presentation.registeration.component.TextHeader

@Composable
fun VerifyScreen() {
    val imageRes = R.drawable.verify
    Column(
        Modifier
            .fillMaxSize()
            .padding(
                horizontal = Dimens.largePadding, vertical = Dimens.mediumPadding
            ), verticalArrangement = Arrangement.spacedBy(Dimens.largePadding)
    ) {
        ImageHeader(imageRes = imageRes)
        TextHeader(headerString = "Verification Code")
        DescriptionHeader(text = "Confirm With Code")
        VerifyFiled()
        Spacer(modifier = Modifier.weight(1f))
        PrimaryButton(text = "Verify", onClick = { /*TODO*/ })
    }
}

