package com.example.icare.presentation.registeration.signup

import PrimaryButton
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.navigation.NavController
import com.example.icare.core.util.Dimens
import com.example.icare.core.util.ButtonHeight
import com.example.icare.core.util.reusablecomponent.PrimaryInputTextFiled
import com.example.icare.core.util.reusablecomponent.PasswordInputField
import com.example.icare.R
import com.example.icare.core.theme.black
import com.example.icare.core.theme.blue
import com.example.icare.core.theme.green500
import com.example.icare.core.util.WidthSpacer
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
        InputFields(signUpViewModel = signUpViewModel, context = context)
        AgreementText()
        ContinueButton(signUpViewModel = signUpViewModel)
        SignInText()
    }
}

@Composable
private fun ContinueButton(signUpViewModel: SignUpViewModel) {
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
}

@Composable
private fun InputFields(signUpViewModel: SignUpViewModel, context: Context) {
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
}

@Composable
private fun AgreementText() {
    val text = buildAnnotatedString {
        withStyle(style = SpanStyle(color = black)) {
            append("By signing up, you’re agree to our ")
        }
        withStyle(style = SpanStyle(color = blue)) {
            append("Terms & Conditions ")
        }
        append("and ")
        withStyle(style = SpanStyle(color = blue)) {
            append("Privacy Policy")
        }

    }
    Text(
        text = text,
        style = MaterialTheme.typography.titleSmall
    )
}

@Composable
private fun SignInText() {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(
            text = stringResource(id = R.string.joined_before),
            style = MaterialTheme.typography.titleSmall
        )
        WidthSpacer()
        Text(
            text = stringResource(id = R.string.sign_in),
            style = MaterialTheme.typography.titleSmall,
            color = green500,
            modifier = Modifier.clickable {
                //todo:
            }
        )
    }
}