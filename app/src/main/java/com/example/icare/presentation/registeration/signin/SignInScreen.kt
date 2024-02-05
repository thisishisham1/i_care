package com.example.icare.presentation.registeration.signin

import PrimaryButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.icare.presentation.registeration.signin.component.ForgotPassword
import com.example.icare.presentation.registeration.signin.component.SignInHeader
import com.example.icare.presentation.registeration.signin.component.SignInImage
import com.example.icare.presentation.registeration.signin.component.SignUpText
import com.example.icare.core.util.ButtonHeight
import com.example.icare.core.util.Dimens
import com.example.icare.core.util.reusablecomponent.InputTextFiled
import com.example.icare.core.util.reusablecomponent.PasswordInputField
import com.example.icare.R

@Composable
fun SignInScreen() {
    Column(
        verticalArrangement = Arrangement.spacedBy(Dimens.smallPadding),
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = Dimens.mediumPadding, horizontal = Dimens.largePadding
            )
    ) {
        SignInImage()
        SignInHeader()
        val inputFiled = mutableMapOf(
            stringResource(id = R.string.email) to remember {
                mutableStateOf("")
            },
            stringResource(id = R.string.password) to remember {
                mutableStateOf("")
            }
        )
        inputFiled.forEach { (label, valueState) ->
            when (label) {
                stringResource(id = R.string.email) -> InputTextFiled(
                    value = valueState.value,
                    label = label, onValueChange = { valueState.value = it }
                )

                else -> PasswordInputField(
                    label = label,
                    onValueChange = { valueState.value = it },
                    value = valueState.value
                )
            }
        }
        ForgotPassword()
        Spacer(modifier = Modifier.height(Dimens.mediumPadding))
        PrimaryButton(
            text = stringResource(id = R.string.login),
            onClick = { /*TODO:implementation of login button*/ },
            modifier = Modifier
                .fillMaxWidth()
                .height(
                    ButtonHeight
                )
        )
        SignUpText()
    }
}