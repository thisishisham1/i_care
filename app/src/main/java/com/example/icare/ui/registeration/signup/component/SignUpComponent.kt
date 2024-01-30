package com.example.icare.ui.registeration.signup.component


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.icare.R
import com.example.icare.ui.theme.black
import com.example.icare.ui.theme.blue
import com.example.icare.ui.theme.green500
import com.example.icare.util.SizeRegisterationImage

@Composable
fun SignupImage() {
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(SignUpImage).crossfade(true).build(),
            contentDescription = "SignUp Image",
            Modifier.requiredSize(SizeRegisterationImage)
        )
    }
}

@Composable
fun SignUpHeader() {
    Text(
        text = "SignUp",
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier
            .fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Composable
fun AgreementText() {
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
fun SignInText() {
    val text = buildAnnotatedString {
        withStyle(style = SpanStyle(color = black)) {
            append("Joined us before? ")
        }
        withStyle(style = SpanStyle(color = green500)) {
            append("Sign In")
        }

    }
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall, modifier = Modifier.clickable {
                handleSignInClick()
            }
        )
    }

}

private fun handleSignInClick() {
    // TODO: Implement the action when the "Sign In" text is clicked
}

private val SignUpImage = R.drawable.signup