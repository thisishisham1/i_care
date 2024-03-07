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
import com.example.icare.R
import com.example.icare.core.theme.blue
import com.example.icare.core.theme.green500
import com.example.icare.core.util.WidthSpacer

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