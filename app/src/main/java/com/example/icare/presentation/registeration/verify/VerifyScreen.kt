package com.example.icare.presentation.registeration.verify

import PrimaryButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.icare.R
import com.example.icare.core.theme.gray400
import com.example.icare.core.theme.shapes
import com.example.icare.core.util.Dimens
import com.example.icare.presentation.registeration.component.DescriptionHeader
import com.example.icare.presentation.registeration.component.ImageHeader
import com.example.icare.presentation.registeration.component.TextHeader

@Composable
fun VerifyScreen(navController: NavHostController) {
    val imageRes = R.drawable.verify
    val verificationCode = stringResource(id = R.string.verification_code)
    val confirmWithCode = stringResource(id = R.string.confirm_with_code)
    val verify = stringResource(id = R.string.verify)
    Column(
        Modifier
            .fillMaxSize()
            .padding(
                horizontal = Dimens.largePadding, vertical = Dimens.mediumPadding
            ), verticalArrangement = Arrangement.spacedBy(Dimens.largePadding)
    ) {
        ImageHeader(imageRes = imageRes)
        TextHeader(headerString = verificationCode)
        DescriptionHeader(text = confirmWithCode)
        VerifyFiled()
        Spacer(modifier = Modifier.weight(1f))
        PrimaryButton(text = verify, onClick = { /*TODO*/ })
    }
}


@Composable
private fun VerifyFiled() {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        (1..4).forEach {
            VerifyNumberContainer(number = it)
        }
    }

}

@Composable
private fun VerifyNumberContainer(number: Int) {
    Box(
        modifier = Modifier
            .requiredSize(80.dp)
            .clip(shape = shapes.medium)
            .background(
                gray400.copy(alpha = 0.3f)
            ), contentAlignment = Alignment.Center
    ) {
        Text(
            text = number.toString(),
            style = MaterialTheme.typography.headlineLarge.copy(fontSize = 40.sp),
        )
    }
}