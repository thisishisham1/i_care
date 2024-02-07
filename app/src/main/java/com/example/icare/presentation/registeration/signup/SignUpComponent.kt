package com.example.icare.presentation.registeration.signup


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.example.icare.R
import com.example.icare.core.theme.black
import com.example.icare.core.theme.blue
import com.example.icare.core.theme.green500

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