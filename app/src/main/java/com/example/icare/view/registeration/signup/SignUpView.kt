package com.example.icare.view.registeration.signup

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.icare.R
import com.example.icare.core.Dimens
import com.example.icare.core.reusablecomponent.PasswordInputField
import com.example.icare.core.reusablecomponent.PrimaryInputTextFiled
import com.example.icare.core.reusablecomponent.WidthSpacer
import com.example.icare.core.theme.green500
import com.example.icare.core.theme.neutralWhite
import com.example.icare.model.classes.Destinations
import com.example.icare.view.registeration.component.CheckboxComponent
import com.example.icare.view.registeration.component.ImageHeader
import com.example.icare.view.registeration.component.TextHeader
import com.example.icare.viewmodel.registeration.signup.SignUpViewModel

private val imageRes = R.drawable.signup

@Composable
fun SignUpView(navController: NavController) {
    val signUpViewModel = remember {
        SignUpViewModel(navController = navController)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = Dimens.largePadding, vertical = Dimens.mediumPadding),
    ) {
        ImageHeader(imageRes = imageRes)
        TextHeader(headerString = "Sign up")
        InputFields(signUpViewModel = signUpViewModel)
        CheckboxComponent(onTextSelected = {
            navController.navigate(Destinations.Main.TermsAndConditions.route)
        }, onCheckedChange = {
            signUpViewModel.onEvent(SignupUIEvent.PrivacyPolicyCheckBoxClicked(it))
        })
        ContinueButton(signUpViewModel = signUpViewModel)
        SignInText(navController)
    }
}

@Composable
private fun ContinueButton(signUpViewModel: SignUpViewModel) {
    Button(
        onClick = { signUpViewModel.onEvent(SignupUIEvent.RegisterButtonClicked) },
        shape = Shapes().medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = green500, contentColor = neutralWhite
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
    ) {
        if (signUpViewModel.isRegistrationInProgress.value) {
            CircularProgressIndicator(color = neutralWhite)
        } else
            Text(text = "Login", style = MaterialTheme.typography.titleLarge.copy(fontSize = 23.sp))
    }
}

@Composable
private fun InputFields(signUpViewModel: SignUpViewModel) {
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
            isError = signUpViewModel.registrationUiState.value.completeNameError,
            value = name.value,
            onValueChange = {
                name.value = it
                signUpViewModel.onEvent(SignupUIEvent.CompleteNameChanged(it))
            },
            label = "Name"
        )
        PrimaryInputTextFiled(
            errorMessage = if (signUpViewModel.registrationUiState.value.emailError) signUpViewModel.registrationUiState.value.errorMessageForEmail else null,
            isError = signUpViewModel.registrationUiState.value.emailError,
            value = email.value,
            onValueChange = {
                email.value = it
                signUpViewModel.onEvent(SignupUIEvent.EmailChanged(it))
            },
            label = "Email"
        )
        PrimaryInputTextFiled(
            isError = signUpViewModel.registrationUiState.value.phoneError,
            value = phone.value,
            onValueChange = {
                phone.value = it
                signUpViewModel.onEvent(SignupUIEvent.PhoneChanged(it))
            },
            label = "Phone"
        )
        PasswordInputField(
            isError = signUpViewModel.registrationUiState.value.passwordError,
            label = "Password",
            onValueChange = {
                password.value = it
                signUpViewModel.onEvent(SignupUIEvent.PasswordChanged(it))
            },
            value = password.value
        )
        PasswordInputField(
            isError = signUpViewModel.registrationUiState.value.confirmPasswordError,
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
private fun SignInText(navController: NavController) {
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
                navController.navigate(Destinations.Auth.Login.route) {
                    popUpTo(0)
                }
            }
        )
    }
}