package com.example.icare.ui.registeration.signup

import PrimaryButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.icare.util.Dimens
import com.example.icare.ui.registeration.signup.component.AgreementText
import com.example.icare.ui.registeration.signup.component.SignInText
import com.example.icare.ui.registeration.signup.component.SignUpHeader
import com.example.icare.ui.registeration.signup.component.SignupImage
import com.example.icare.util.ButtonHeight
import com.example.icare.util.reusablecomponent.InputTextFiled
import com.example.icare.util.reusablecomponent.PasswordInputField

@Composable
fun SignUpScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = Dimens.largePadding, vertical = Dimens.mediumPadding),
        verticalArrangement = Arrangement.spacedBy(Dimens.smallPadding)
    ) {
        SignupImage()

        SignUpHeader()

        val inputFields = mutableMapOf(
            "Name" to remember {
                mutableStateOf("")
            },
            "Email" to remember {
                mutableStateOf("")
            },
            "Password" to remember {
                mutableStateOf("")
            },
            "Confirm Password" to remember {
                mutableStateOf("")
            }
        )
        inputFields.forEach { (label, valueState) ->
            Spacer(modifier = Modifier.height(Dimens.mediumPadding))
            when (label) {
                "Password", "Confirm Password" -> {
                    PasswordInputField(
                        label = label,
                        onValueChange = { valueState.value = it },
                        value = valueState.value
                    )
                }


                else -> {
                    InputTextFiled(
                        value = valueState.value,
                        onValueChange = {
                            valueState.value = it
                        },
                        label = label
                    )
                }
            }
        }

        AgreementText()
        Spacer(modifier = Modifier.weight(1f))
        PrimaryButton(
            text = "Continue",
            onClick = { handleContinueClick() },
            modifier = Modifier
                .fillMaxWidth()
                .height(ButtonHeight),
            textStyle = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
        )

        SignInText()
    }
}

private fun handleContinueClick() {
    // TODO: Implement the action when the "Continue" button is clicked
}