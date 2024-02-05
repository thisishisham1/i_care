package com.example.icare.presentation.registeration.signin.component

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
fun SignInImage() {
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(SignInImage).crossfade(true).build(),
            contentDescription = "SignIn Image", Modifier.requiredSize(SizeImage)
        )
    }
}

@Composable
fun SignInHeader() {
    Text(
        text = stringResource(id = R.string.sign_in),
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier
            .fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Composable
fun ForgotPassword() {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        Text(
            text = stringResource(id = R.string.forgot_password),
            style = MaterialTheme.typography.titleSmall, color = blue,
            modifier = Modifier.clickable { },
        )
    }

}

@Composable
fun SignUpText() {
    val text = buildAnnotatedString {
        withStyle(SpanStyle(color = black)) {
            append(stringResource(id = R.string.dont_have_account))
        }

        withStyle(SpanStyle(color = green500)) {
            append(stringResource(id = R.string.sign_up))
        }
    }
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(text = text, style = MaterialTheme.typography.titleSmall)
    }
}

private val SignInImage = R.drawable.signin