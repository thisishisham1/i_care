package com.example.icare.presentation.registeration.signin

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.navigation.NavController
import com.example.icare.R
import com.example.icare.core.theme.black
import com.example.icare.core.theme.blue
import com.example.icare.core.theme.green500
import com.example.icare.core.util.Destinations
import com.example.icare.core.util.WidthSpacer
import com.example.icare.core.util.navigateAndClearStack
import com.example.icare.presentation.registeration.RegisterViewModel

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
fun SignUpText(viewModel: RegisterViewModel) {
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
                // TODO:
            }
        )
    }
}