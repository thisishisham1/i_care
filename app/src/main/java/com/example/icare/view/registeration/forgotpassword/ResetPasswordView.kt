package com.example.icare.view.registeration.forgotpassword

import PrimaryButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.icare.R
import com.example.icare.core.Dimens
import com.example.icare.core.reusablecomponent.HeightSpacer
import com.example.icare.core.reusablecomponent.PasswordInputField
import com.example.icare.view.registeration.component.ImageHeader
import com.example.icare.view.registeration.component.TextHeader
import com.example.icare.viewmodel.registeration.forgotpassword.ForgotPasswordViewModel

@Composable
fun ResetPasswordView(navController: NavHostController) {
    val resetPasswordViewModel = remember {
        ForgotPasswordViewModel(navController)
    }
    val resetPassword = stringResource(id = R.string.reset_password)
    val changePassword = stringResource(id = R.string.change_password)
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.largePadding, vertical = Dimens.mediumPadding),
        horizontalAlignment =
        Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.mediumPadding)
    ) {
        ImageHeader(imageRes = R.drawable.reset_password, sizeImage = 320.dp)
        TextHeader(headerString = resetPassword)
        HeightSpacer()
        InputFields(resetPasswordViewModel = resetPasswordViewModel)
        Spacer(modifier = Modifier.weight(1f))
        PrimaryButton(
            text = changePassword,
            onClick = { resetPasswordViewModel.handleChangePasswordButton() })
    }
}

@Composable
private fun InputFields(resetPasswordViewModel: ForgotPasswordViewModel) {
    val context = LocalContext.current
    resetPasswordViewModel.passwordFields.forEach { (label, value) ->
        HeightSpacer()
        PasswordInputField(
            isError = resetPasswordViewModel.isError(label),
            errorMessage = resetPasswordViewModel.getErrorMessage(context, label),
            label = label,
            onValueChange = { value.value = it },
            value = value.value
        )
    }
}