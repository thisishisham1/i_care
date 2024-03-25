package com.example.icare.presentation.registeration.forgotpassword

import PrimaryButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.icare.R
import com.example.icare.core.util.Dimens
import com.example.icare.core.util.reusablecomponent.PrimaryInputTextFiled
import com.example.icare.presentation.registeration.component.DescriptionHeader
import com.example.icare.presentation.registeration.component.ImageHeader
import com.example.icare.presentation.registeration.component.TextHeader

private val imageRes = R.drawable.forgot

@Composable
fun ForgotPasswordScreen(navController: NavHostController) {
    val resetPasswordViewModel = remember {
        ResetPasswordViewModel(navController)
    }
    val forgotPasswordText = stringResource(id = R.string.forgot_password)
    val descriptionForgotPassword = stringResource(id = R.string.description_forgot_password)
    val submit = stringResource(id = R.string.submit)
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
        InputFiled(resetPasswordViewModel = resetPasswordViewModel)
        Spacer(modifier = Modifier.weight(1f))
        PrimaryButton(text = submit, onClick = { resetPasswordViewModel.handleSubmitButton() })
    }
}

@Composable
private fun InputFiled(resetPasswordViewModel: ResetPasswordViewModel) {
    val context = LocalContext.current
    val label = stringResource(id = R.string.email)
    PrimaryInputTextFiled(
        isError = resetPasswordViewModel.isError(),
        errorMessage = resetPasswordViewModel.getErrorMessage(context),
        value = resetPasswordViewModel.emailValue.value,
        onValueChange = { resetPasswordViewModel.emailValue.value = it },
        label = label
    )
}