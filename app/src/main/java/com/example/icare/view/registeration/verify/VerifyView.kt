package com.example.icare.view.registeration.verify

import PrimaryButton
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.icare.R
import com.example.icare.core.theme.gray400
import com.example.icare.core.theme.shapes
import com.example.icare.core.util.Dimens
import com.example.icare.view.registeration.component.DescriptionHeader
import com.example.icare.view.registeration.component.ImageHeader
import com.example.icare.view.registeration.component.TextHeader
import com.example.icare.viewmodel.registeration.verify.VerifyViewModel

@Composable
fun VerifyView(navController: NavHostController) {
    val verifyViewModel = remember {
        VerifyViewModel(navController)
    }
    val imageRes = R.drawable.verify
    val verificationCode = stringResource(id = R.string.verification_code)
    val confirmWithCode = stringResource(id = R.string.confirm_with_code)
    val verify = stringResource(id = R.string.verify)
    Column(
        Modifier
            .fillMaxSize()
            .padding(
                horizontal = Dimens.largePadding, vertical = Dimens.mediumPadding
            ), verticalArrangement = Arrangement.spacedBy(Dimens.largePadding)
    ) {
        ImageHeader(imageRes = imageRes)
        TextHeader(headerString = verificationCode)
        DescriptionHeader(text = confirmWithCode)
        VerifyFiled(verifyViewModel)
        Spacer(modifier = Modifier.weight(1f))
        PrimaryButton(text = verify, onClick = { verifyViewModel.handleVerifyButton() })
    }
}


@Composable
private fun VerifyFiled(verifyViewModel: VerifyViewModel) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        val textFocusManager = LocalFocusManager.current
        verifyViewModel.textFieldStates.forEachIndexed { index, value ->
            VerifyNumberContainer(
                value = value.value,
                isError = verifyViewModel.isError.value,
                onValueChange = { newValue ->
                    val numberPattern = Regex("^[0-9]*$")
                    if (newValue.matches(numberPattern)) {
                        verifyViewModel.textFieldStates[index].value = newValue.takeLast(1)
                        if (newValue.isEmpty() && index > 0) {
                            textFocusManager.moveFocus(FocusDirection.Previous)
                        } else if (newValue.length == 1 && index < verifyViewModel.textFieldStates.lastIndex) {
                            textFocusManager.moveFocus(FocusDirection.Next)
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun VerifyNumberContainer(
    onValueChange: (String) -> Unit,
    value: String,
    isError: Boolean
) {
    val borderColor = if (isError) MaterialTheme.colorScheme.error else Color.Transparent
    Box(
        modifier = Modifier
            .requiredSize(80.dp)
            .clip(shape = shapes.medium)
            .border(width = 2.dp, color = borderColor, shape = shapes.medium)
            .background(
                gray400.copy(alpha = 0.3f)
            ), contentAlignment = Alignment.Center
    ) {
        TextField(
            isError = isError,
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            value = value,
            maxLines = 1,
            onValueChange = onValueChange,
            textStyle = MaterialTheme.typography.headlineLarge.copy(fontSize = 40.sp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                errorContainerColor = Color.Transparent
            )
        )
    }
}