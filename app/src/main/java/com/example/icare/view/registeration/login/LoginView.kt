package com.example.icare.view.registeration.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.icare.R
import com.example.icare.core.Dimens
import com.example.icare.core.reusablecomponent.PrimaryInputTextFiled
import com.example.icare.core.reusablecomponent.ProgressIndicator
import com.example.icare.core.reusablecomponent.WidthSpacer
import com.example.icare.core.theme.blue
import com.example.icare.core.theme.green500
import com.example.icare.core.theme.neutralWhite
import com.example.icare.model.sharedPreferences.PreferencesHelper
import com.example.icare.view.registeration.component.ImageHeader
import com.example.icare.view.registeration.component.TextHeader
import com.example.icare.viewmodel.registeration.login.LoginViewModel

private val imageRes = R.drawable.signin

@Composable
fun LoginView(navController: NavController, preferencesHelper: PreferencesHelper) {
    val vm = remember { LoginViewModel(navController, preferencesHelper) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(
                vertical = Dimens.mediumPadding, horizontal = Dimens.largePadding
            ),
        verticalArrangement = Arrangement.spacedBy(Dimens.smallPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageHeader(imageRes = imageRes)
        TextHeader(headerString = "Sign In")
        InputFields(vm = vm)
        ErrorMessage(
            errorMessage = vm.errorMessage.value,
            isError = vm.errorMessage.value != null,
            modifier = Modifier.align(Alignment.Start)
        )
        ForgotPassword(vm)
        Spacer(modifier = Modifier.height(Dimens.mediumPadding))
        SignInButton(loginViewModel = vm)
        GoogleButton()
        SignUpText(vm)
    }
}

@Composable
private fun InputFields(vm: LoginViewModel) {
    val emailValue = remember {
        mutableStateOf("")
    }
    val passwordValue = remember {
        mutableStateOf("")
    }
    var isPasswordVisible by remember {
        mutableStateOf(true)
    }
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        PrimaryInputTextFiled(isError = vm.loginUiState.value.isEmailError,
            value = emailValue.value,
            label = stringResource(id = R.string.email),
            onValueChange = {
                emailValue.value = it
                vm.onEvent(LoginUIEvent.EmailChanged(it))
            })
        PrimaryInputTextFiled(
            isError = vm.loginUiState.value.isPasswordError,
            value = passwordValue.value,
            onValueChange = {
                passwordValue.value = it
                vm.onEvent(LoginUIEvent.PasswordChanged(it))
            },
            label = stringResource(id = R.string.password), isPassword = isPasswordVisible,
            trailingIcon = {
                val iconRes = if (isPasswordVisible) R.drawable.show else R.drawable.hide
                Icon(
                    painterResource(id = iconRes),
                    contentDescription = "",
                    Modifier
                        .requiredSize(if (isPasswordVisible) 25.dp else 20.dp)
                        .clickable {
                            isPasswordVisible = !isPasswordVisible
                        },
                )
            },
        )

    }


}

@Composable
private fun SignInButton(loginViewModel: LoginViewModel) {
    Button(
        onClick = { loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked) },
        shape = Shapes().medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = green500, contentColor = neutralWhite
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp),
    ) {

        if (loginViewModel.isLoginInProgress.value) {
            ProgressIndicator(color = neutralWhite)
        } else {
            Text(
                text = "Login", style = MaterialTheme.typography.titleLarge.copy(fontSize = 23.sp)
            )

        }

    }
}

@Composable
private fun GoogleButton(
    text: String = "Sign in with google"
) {
    var clicked by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .border(
                width = 2.dp, color = green500, // Define your border color
                shape = Shapes().medium
            )
    ) {
        Button(
            onClick = { clicked = !clicked },
            modifier = Modifier.fillMaxSize(),
            shape = Shapes().medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = neutralWhite
            )
        ) {
            Row(
                Modifier
                    .fillMaxSize()
                    .animateContentSize(
                        animationSpec = tween(
                            durationMillis = 300, easing = FastOutLinearInEasing
                        )
                    ),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (!clicked) {
                    Image(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = "google icon",
                        modifier = Modifier.size(25.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = text, style = MaterialTheme.typography.titleLarge, color = green500
                    )
                } else {
                    ProgressIndicator()
                }

            }
        }
    }
}


@Composable
private fun ForgotPassword(loginViewModel: LoginViewModel) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        Text(
            text = stringResource(id = R.string.forgot_password),
            style = MaterialTheme.typography.titleSmall, color = blue,
            modifier = Modifier.clickable { loginViewModel.handleForgotPasswordButton() },
        )
    }

}

@Composable
private fun SignUpText(loginViewModel: LoginViewModel) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(
            text = stringResource(id = R.string.dont_have_account),
            style = MaterialTheme.typography.titleSmall
        )
        WidthSpacer()
        Text(text = stringResource(id = R.string.sign_up),
            style = MaterialTheme.typography.titleSmall,
            color = green500,
            modifier = Modifier.clickable {
                loginViewModel.handleSignUpButton()
            })
    }
}


@Composable
fun ErrorMessage(isError: Boolean, errorMessage: String?, modifier: Modifier) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.Start) {
        AnimatedVisibility(
            visible = isError,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            Text(
                text = errorMessage ?: "",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}