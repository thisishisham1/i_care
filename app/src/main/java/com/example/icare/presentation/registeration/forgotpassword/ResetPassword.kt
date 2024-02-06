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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.icare.R
import com.example.icare.core.util.Dimens
import com.example.icare.core.util.HeightSpacer
import com.example.icare.core.util.reusablecomponent.PasswordInputField
import com.example.icare.presentation.registeration.component.ImageHeader
import com.example.icare.presentation.registeration.component.TextHeader

@Composable
fun ResetPasswordScreen() {
    val password = remember {
        mutableStateOf("")
    }
    val confirmPassword = remember {
        mutableStateOf("")
    }
    val passwordFiled = listOf("Password" to password, "Confirm Password" to confirmPassword)
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.largePadding, vertical = Dimens.mediumPadding),
        horizontalAlignment =
        Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.mediumPadding)
    ) {
        ImageHeader(imageRes = R.drawable.reset_password, sizeImage = 320.dp)
        TextHeader(headerString = "Reset Password?")
        HeightSpacer()
        passwordFiled.forEach { (label, value) ->
            HeightSpacer()
            PasswordInputField(
                label = label,
                onValueChange = { value.value = it },
                value = value.value
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        PrimaryButton(text = "Change Password", onClick = { /*TODO*/ })
    }
}