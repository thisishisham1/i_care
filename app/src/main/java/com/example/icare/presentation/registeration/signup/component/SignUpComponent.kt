package com.example.icare.presentation.registeration.signup.component


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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.icare.R
import com.example.icare.core.theme.black
import com.example.icare.core.theme.blue
import com.example.icare.core.theme.green500
import com.example.icare.core.util.SizeImage

@Composable
fun SignupImage() {
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(SignUpImage).crossfade(true).build(),
            contentDescription = "SignUp Image",
            Modifier.requiredSize(SizeImage)
        )
    }
}

@Composable
fun SignUpHeader() {
    Text(
        text = stringResource(id = R.string.sign_up),
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
            append(stringResource(id = R.string.joined_before))
        }
        withStyle(style = SpanStyle(color = green500)) {
            append(stringResource(id = R.string.sign_in))
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