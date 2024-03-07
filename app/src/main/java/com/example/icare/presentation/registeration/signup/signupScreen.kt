package com.example.icare.presentation.registeration.signup

import PrimaryButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.icare.core.util.Dimens
import com.example.icare.core.util.ButtonHeight
import com.example.icare.core.util.reusablecomponent.PrimaryInputTextFiled
import com.example.icare.core.util.reusablecomponent.PasswordInputField
import com.example.icare.R
import com.example.icare.presentation.registeration.component.ImageHeader
import com.example.icare.presentation.registeration.component.TextHeader

private val imageRes = R.drawable.signup

@Composable
fun SignUpScreen(navController: NavController) {
    val signUpViewModel = remember {
        SignUpViewModel()
    }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = Dimens.largePadding, vertical = Dimens.mediumPadding),
    ) {
        ImageHeader(imageRes = imageRes)
        TextHeader(headerString = "Sign up")
        signUpViewModel.inputFields.forEach { (label, valueState) ->
            Spacer(modifier = Modifier.height(Dimens.mediumPadding))
            when (label) {
                "Password", "Confirm Password" -> {
                    PasswordInputField(
                        isError = signUpViewModel.isError(label),
                        errorMessage = signUpViewModel.errorMessage(label, context),
                        label = label,
                        onValueChange = { valueState.value = it },
                        value = valueState.value
                    )
                }

                else -> {
                    PrimaryInputTextFiled(
                        isError = signUpViewModel.isError(label),
                        errorMessage = signUpViewModel.errorMessage(label, context),
                        value = valueState.value,
                        onValueChange = {
                            valueState.value = it
                        },
                        label = label
                    )
                }
            }
        }
        AgreementText()
        PrimaryButton(
            text = stringResource(id = R.string.continue_),
            onClick = {
                signUpViewModel.handleContinueClick()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(ButtonHeight),
            textStyle = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
        )
        SignInText()
    }
}