package com.example.icare.presentation.registeration.signin

import PrimaryButton
import android.util.Log
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
import androidx.navigation.NavController
import com.example.icare.core.util.ButtonHeight
import com.example.icare.core.util.Dimens
import com.example.icare.core.util.reusablecomponent.PrimaryInputTextFiled
import com.example.icare.core.util.reusablecomponent.PasswordInputField
import com.example.icare.R
import com.example.icare.presentation.registeration.RegisterViewModel
import com.example.icare.presentation.registeration.component.ImageHeader
import com.example.icare.presentation.registeration.component.TextHeader

private val imageRes = R.drawable.signin

@Composable
fun SignInScreen(navController: NavController) {
    val vm = remember {
        RegisterViewModel()
    }
    val emailState = remember {
        mutableStateOf("")
    }
    val passwordState = remember {
        mutableStateOf("")
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(Dimens.smallPadding),
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = Dimens.mediumPadding, horizontal = Dimens.largePadding
            )
    ) {
        ImageHeader(imageRes = imageRes)
        TextHeader("Sign In")

        PrimaryInputTextFiled(
            value = emailState.value,
            label = stringResource(id = R.string.email), onValueChange = { emailState.value = it }
        )

        PasswordInputField(
            label = stringResource(id = R.string.password),
            onValueChange = { passwordState.value = it },
            value = passwordState.value
        )
        ForgotPassword()
        Spacer(modifier = Modifier.height(Dimens.mediumPadding))
        PrimaryButton(
            text = stringResource(id = R.string.login),
            onClick = {
                //todo:
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(
                    ButtonHeight
                )
        )
        SignUpText(viewModel = vm)
    }
}