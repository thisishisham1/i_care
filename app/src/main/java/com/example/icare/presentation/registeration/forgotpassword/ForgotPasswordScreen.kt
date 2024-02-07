package com.example.icare.presentation.registeration.forgotpassword

import PrimaryButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.icare.R
import com.example.icare.core.util.Dimens
import com.example.icare.core.util.reusablecomponent.PrimaryInputTextFiled
import com.example.icare.presentation.registeration.component.DescriptionHeader
import com.example.icare.presentation.registeration.component.ImageHeader
import com.example.icare.presentation.registeration.component.TextHeader

private val imageRes = R.drawable.forgot

@Composable
fun ForgotPasswordScreen() {
    val forgotPasswordText = stringResource(id = R.string.forgot_password)
    val descriptionForgotPassword = stringResource(id = R.string.description_Forgot_password)
    val submit = stringResource(id = R.string.submit)
    val label = stringResource(id = R.string.email)
    val value = remember {
        mutableStateOf("")
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(
                horizontal = Dimens.largePadding, vertical = Dimens.mediumPadding
            ), verticalArrangement = Arrangement.spacedBy(Dimens.largePadding)
    ) {
        ImageHeader(imageRes = imageRes)
        TextHeader(headerString = forgotPasswordText)
        DescriptionHeader(descriptionForgotPassword)
        PrimaryInputTextFiled(
            value = value.value,
            onValueChange = { value.value = it },
            label = label
        )
        Spacer(modifier = Modifier.weight(1f))
        PrimaryButton(text = submit, onClick = { /*TODO*/ })
    }
}