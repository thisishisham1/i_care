package com.example.icare.view.registeration.signup

import PrimaryButton
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.icare.R
import com.example.icare.core.Dimens
import com.example.icare.core.reusablecomponent.BackArrow
import com.example.icare.core.reusablecomponent.PrimaryInputTextFiled
import com.example.icare.model.classes.Destinations
import com.example.icare.view.registeration.component.CheckboxComponent
import com.example.icare.view.registeration.component.ImageHeader
import com.example.icare.viewmodel.registeration.signup.SignUpViewModel

private val imageRes = R.drawable.signup

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpView(navController: NavController) {
    val vm = remember {
        SignUpViewModel(navController = navController)
    }
    Scaffold(topBar = {
        CenterAlignedTopAppBar( // Use CenterAlignedTopAppBar for Material 3
            title = { Text(text = stringResource(id = R.string.sign_up)) }, //Set title
            navigationIcon = { BackArrow(navController = navController) })
    }) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = Dimens.largePadding, vertical = Dimens.mediumPadding)
                .padding(
                    bottom = innerPadding.calculateBottomPadding(),
                    top = innerPadding.calculateTopPadding()
                ),
        ) {
            ImageHeader(imageRes = imageRes)
            InputFields(signUpViewModel = vm)
            ErrorMessage(
                errorMessage = vm.errorMessage.value,
                isError = vm.errorMessage.value != null,
                modifier = Modifier.align(Alignment.Start)
            )

            CheckboxComponent(onTextSelected = {
                navController.navigate(Destinations.Main.TermsAndConditions.route)
            }, onCheckedChange = {
                vm.onEvent(SignupUIEvent.PrivacyPolicyCheckBoxClicked(it))
            })
            ContinueButton(vm = vm)
            Spacer(modifier = Modifier.height(Dimens.smallPadding))
            SignInText(navController)
        }
    }
}


@Composable
private fun ContinueButton(vm: SignUpViewModel) {


    PrimaryButton(
        text = stringResource(id = R.string.continue_),
        onClick = { vm.onEvent(SignupUIEvent.RegisterButtonClicked) },
        isEnabled = !vm.isRegistrationInProgress.value,
        isLoading = vm.isRegistrationInProgress.value
    )
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
    Column(verticalArrangement = Arrangement.spacedBy(Dimens.mediumPadding)) {
        PrimaryInputTextFiled(
            isError = signUpViewModel.registrationUiState.value.isNameError,
            value = name.value,
            onValueChange = {
                name.value = it
                signUpViewModel.onEvent(SignupUIEvent.CompleteNameChanged(it))
            },
            label = "Name"
        )
        PrimaryInputTextFiled(
            isError = signUpViewModel.registrationUiState.value.isEmailError,
            value = email.value,
            onValueChange = {
                email.value = it
                signUpViewModel.onEvent(SignupUIEvent.EmailChanged(it))
            },
            label = "Email"
        )
        PrimaryInputTextFiled(
            isError = signUpViewModel.registrationUiState.value.isPhoneError,
            value = phone.value,
            onValueChange = {
                phone.value = it
                signUpViewModel.onEvent(SignupUIEvent.PhoneChanged(it))
            },
            label = "Phone"
        )
        var isPasswordVisible by remember {
            mutableStateOf(true)
        }
        PrimaryInputTextFiled(
            isError = signUpViewModel.registrationUiState.value.isPasswordError,
            value = password.value,
            onValueChange = {
                password.value = it
                signUpViewModel.onEvent(SignupUIEvent.PasswordChanged(it))
            },
            label = stringResource(id = R.string.password), isPassword = isPasswordVisible,
            trailingIcon = {
                val iconRes = if (isPasswordVisible) R.drawable.show else R.drawable.hide
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        painterResource(id = iconRes),
                        contentDescription = if (isPasswordVisible) "Hide Password" else "Show Password", // Add content description
                        modifier = Modifier.size(24.dp)
                    )
                }
            },
        )
    }
}

@Composable
private fun SignInText(navController: NavController) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(
            text = stringResource(id = R.string.joined_before),
            style = MaterialTheme.typography.labelMedium
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = stringResource(id = R.string.sign_in),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable {
                navController.navigate(Destinations.Auth.Login.route) {
                    popUpTo(0)
                }
            })
    }
}

@Composable
fun ErrorMessage(isError: Boolean, errorMessage: String?, modifier: Modifier) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.Start) {
        AnimatedVisibility(
            visible = isError,
            enter = fadeIn() + slideInHorizontally(),
            exit = fadeOut() + slideOutHorizontally()
        ) {
            Text(
                text = errorMessage ?: "",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}