package com.example.icare.presentation.registeration.signup

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.icare.core.util.Dimens
import com.example.icare.core.util.ButtonHeight
import com.example.icare.core.util.reusablecomponent.PrimaryInputTextFiled
import com.example.icare.core.util.reusablecomponent.PasswordInputField
import com.example.icare.R
import com.example.icare.core.theme.black
import com.example.icare.core.theme.blue
import com.example.icare.core.theme.green500
import com.example.icare.core.theme.neutralWhite
import com.example.icare.core.util.WidthSpacer
import com.example.icare.data.signup.SignUpViewModel
import com.example.icare.data.signup.SignupUIEvent
import com.example.icare.presentation.registeration.component.ImageHeader
import com.example.icare.presentation.registeration.component.TextHeader

private val imageRes = R.drawable.signup

@Composable
fun SignUpScreen(navController: NavController) {
    val signUpViewModel = remember {
        SignUpViewModel(navController = navController)
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
    Button(
        onClick = { signUpViewModel.onEvent(SignupUIEvent.RegisterButtonClicked) },
        shape = Shapes().medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = green500, contentColor = neutralWhite
        ), modifier = Modifier
            .fillMaxWidth()
            .height(ButtonHeight),
        enabled = signUpViewModel.allValidationsPassed.value
    ) {
        if (signUpViewModel.signUpInProgress.value) {
            CircularProgressIndicator(color = neutralWhite)
        } else
            Text(text = "Login", style = MaterialTheme.typography.titleLarge.copy(fontSize = 23.sp))
    }
}

@Composable
private fun InputFields(signUpViewModel: SignUpViewModel, context: Context) {
    val name = remember {
        mutableStateOf("")
    }
    val email = remember {
        mutableStateOf("")
    }
    val phone = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val confirmPassword = remember {
        mutableStateOf("")
    }
    Column(verticalArrangement = Arrangement.spacedBy(Dimens.mediumPadding)) {
        PrimaryInputTextFiled(
            isError = !signUpViewModel.registrationUIState.value.completeNameError,
            value = name.value,
            onValueChange = {
                name.value = it
                signUpViewModel.onEvent(SignupUIEvent.CompleteNameChanged(it))
            },
            label = "Name"
        )
        PrimaryInputTextFiled(
            isError = !signUpViewModel.registrationUIState.value.emailError,
            value = email.value,
            onValueChange = {
                email.value = it
                signUpViewModel.onEvent(SignupUIEvent.EmailChanged(it))
            },
            label = "Email"
        )
        PrimaryInputTextFiled(
            isError = !signUpViewModel.registrationUIState.value.phoneError,
            value = phone.value,
            onValueChange = {
                phone.value = it
                signUpViewModel.onEvent(SignupUIEvent.PhoneChanged(it))
            },
            label = "Phone"
        )
        PasswordInputField(
            isError = !signUpViewModel.registrationUIState.value.passwordError,
            label = "Password",
            onValueChange = {
                password.value = it
                signUpViewModel.onEvent(SignupUIEvent.PasswordChanged(it))
            },
            value = password.value
        )
        PasswordInputField(
            isError = !signUpViewModel.registrationUIState.value.confirmPasswordError,
            label = "Confirm Password",
            onValueChange = {
                confirmPassword.value = it
                signUpViewModel.onEvent(SignupUIEvent.ConfirmPasswordChange(it))
            },
            value = confirmPassword.value
        )
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