package com.example.icare.ui.presentation.registeration.signup.component


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.icare.ui.util.Dimens
import com.example.icare.R
import com.example.icare.ui.theme.primaryGreen

@Composable
fun SignupImage() {
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.signup),
            contentDescription = "SignUp Image",
            Modifier.requiredSize(270.dp)
        )
    }
}

@Composable
fun SignUpHeader() {
    Text(
        text = "SignUp",
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = Dimens.smallPadding
            ),
        textAlign = TextAlign.Center
    )
}

@Composable
fun AgreementText() {
    Spacer(modifier = Modifier.height(Dimens.mediumPadding))
    Text(
        text = "By signing up, you’re agree to our Terms & Conditions and Privacy Policy",
        style = MaterialTheme.typography.titleSmall
    )
    Spacer(modifier = Modifier.height(Dimens.largePadding))
}

@Composable
fun SignInText() {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(text = "Joined us before?", style = MaterialTheme.typography.titleSmall)
        Text(
            text = "Sign In",
            modifier = Modifier.clickable { handleSignInClick() },
            style = MaterialTheme.typography.titleSmall.copy(color = primaryGreen)
        )
    }

}
private fun handleSignInClick() {
    // TODO: Implement the action when the "Sign In" text is clicked
}