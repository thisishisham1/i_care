package com.example.icare.ui.presentation.registeration.signup

import PrimaryButton
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.icare.ui.util.Dimens
import com.example.icare.R
import com.example.icare.ui.presentation.registeration.signup.component.AgreementText
import com.example.icare.ui.presentation.registeration.signup.component.SignInText
import com.example.icare.ui.presentation.registeration.signup.component.SignUpHeader
import com.example.icare.ui.presentation.registeration.signup.component.SignupImage
import com.example.icare.ui.util.reusablecomponent.InputTextFiled

@Composable
fun SignUp() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.largePadding, vertical = Dimens.mediumPadding)
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
                    InputTextFiled(
                        value = valueState.value,
                        onValueChange = {
                            valueState.value = it
                        },
                        label = label,
                        isPassword = true,
                        trailingIcon = {
                            Icon(
                                painterResource(id = R.drawable.show),
                                contentDescription = "", Modifier.requiredSize(25.dp)
                            )
                        }
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
        PrimaryButton(
            text = "Continue",
            onClick = { handleContinueClick() },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            textStyle = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
        )
        Spacer(modifier = Modifier.height(Dimens.mediumPadding))
        SignInText()
    }
}

private fun handleContinueClick() {
    // TODO: Implement the action when the "Continue" button is clicked
}