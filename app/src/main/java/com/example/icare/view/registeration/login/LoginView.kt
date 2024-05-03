package com.example.icare.view.registeration.login

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.icare.R
import com.example.icare.core.theme.blue
import com.example.icare.core.theme.green500
import com.example.icare.core.theme.neutralWhite
import com.example.icare.core.util.Dimens
import com.example.icare.core.util.reusablecomponent.PasswordInputField
import com.example.icare.core.util.reusablecomponent.PrimaryInputTextFiled
import com.example.icare.core.util.reusablecomponent.WidthSpacer
import com.example.icare.view.registeration.component.ImageHeader
import com.example.icare.view.registeration.component.TextHeader
import com.example.icare.viewmodel.registeration.login.LoginViewModel

private val imageRes = R.drawable.signin

@Composable
fun LoginView(navController: NavController) {
    val signInViewModel = remember { LoginViewModel(navController) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = Dimens.mediumPadding, horizontal = Dimens.largePadding
            ),
        verticalArrangement = Arrangement.spacedBy(Dimens.smallPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageHeader(imageRes = imageRes)
        TextHeader(headerString = "Sign In")
        InputFields(loginViewModel = signInViewModel)
        ForgotPassword(signInViewModel)
        Spacer(modifier = Modifier.height(Dimens.mediumPadding))
        SignInButton(loginViewModel = signInViewModel)
        GoogleButton()
        SignUpText(signInViewModel)
    }
}

@Composable
private fun InputFields(loginViewModel: LoginViewModel) {
    val emailValue = remember {
        mutableStateOf("")
    }
    val passwordValue = remember {
        mutableStateOf("")
    }
    Column {
        PrimaryInputTextFiled(
            isError = loginViewModel.loginUIState.value.emailError,
            value = emailValue.value,
            label = stringResource(id = R.string.email),
            onValueChange = {
                emailValue.value = it
                loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
            }
        )

        PasswordInputField(
            errorMessage = if (loginViewModel.loginUIState.value.passwordError) loginViewModel.loginUIState.value.genericError else null,
            isError = loginViewModel.loginUIState.value.passwordError,
            label = stringResource(id = R.string.password),
            onValueChange = {
                passwordValue.value = it
                loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
            },
            value = passwordValue.value,
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
        if (loginViewModel.loginInProgress.value) {
            CircularProgressIndicator(color = neutralWhite)
        } else
            Text(text = "Login", style = MaterialTheme.typography.titleLarge.copy(fontSize = 23.sp))
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
                width = 2.dp,
                color = green500, // Define your border color
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
                            durationMillis = 300,
                            easing = FastOutLinearInEasing
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
                        text = text,
                        style = MaterialTheme.typography.titleLarge, color = green500
                    )
                } else {
                    ProgressIndicator()
                }

            }
        }
    }
}

@Composable
private fun ProgressIndicator(color: Color = green500) {
    CircularProgressIndicator(
        modifier = Modifier.size(15.dp),
        color = green500,
        strokeWidth = 3.dp
    )
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
        Text(
            text = stringResource(id = R.string.sign_up),
            style = MaterialTheme.typography.titleSmall,
            color = green500,
            modifier = Modifier.clickable {
                loginViewModel.handleSignUpButton()
            }
        )
    }
}