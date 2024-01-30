package com.example.icare.util.reusablecomponent


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.icare.R
import com.example.icare.ui.theme.green500
import com.example.icare.ui.theme.shapes
import com.example.icare.ui.theme.black
import com.example.icare.ui.theme.gray400
import com.example.icare.ui.theme.gray500
import com.example.icare.ui.theme.gray600
import com.example.icare.ui.theme.green200
import com.example.icare.ui.theme.green700
import com.example.icare.ui.theme.red500

@Composable
fun InputTextFiled(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    label: String,
    isPassword: Boolean = false,
    isEnabled: Boolean = true,
    keyboardAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    OutlinedTextField(
        modifier = modifier
            .height(TextFieldHeight)
            .fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        textStyle = textStyle,
        label = {
            Text(text = label, style = MaterialTheme.typography.titleMedium)
        },
        shape = shapes.medium,
        singleLine = true,
        enabled = isEnabled,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = keyboardAction,
            keyboardType = keyboardType
        ),
        colors = OutlinedTextFieldDefaults.colors(
            disabledLabelColor = green500,
            errorLabelColor = red500,
            focusedLabelColor = green700,
            unfocusedLabelColor = gray600,
            disabledBorderColor = green200,
            focusedBorderColor = green500,
            unfocusedBorderColor = gray400,
            errorBorderColor = red500,
            disabledTextColor = gray500,
            focusedTextColor = black,
            unfocusedTextColor = black.copy(alpha = 0.8f),
            cursorColor = green700,
            focusedTrailingIconColor = green500,
            unfocusedTrailingIconColor = gray400
        ),
    )
}

@Composable
fun PasswordInputField(
    label: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit, value: String
) {
    var isPasswordVisible by remember {
        mutableStateOf(true)
    }
    InputTextFiled(
        value = value,
        onValueChange = onValueChange,
        label = label,
        isPassword = isPasswordVisible,
        trailingIcon = {
            val iconRes = if (isPasswordVisible) R.drawable.show else R.drawable.hide
            Icon(
                painterResource(id = iconRes),
                contentDescription = "",
                Modifier
                    .requiredSize(if (isPasswordVisible) 25.dp else 20.dp)
                    .clickable {
                        isPasswordVisible = !isPasswordVisible
                    },
            )


        }, modifier = modifier
    )
}

private val TextFieldHeight = 65.dp