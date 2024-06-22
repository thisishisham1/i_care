package com.example.icare.core.reusablecomponent


import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryInputTextFiled(
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
    isError: Boolean = false,
    errorMessage: String? = null
) {
    val isFocused by remember { mutableStateOf(false) }
    val labelColor = animateColorAsState(
        targetValue = if (isFocused || value.isNotEmpty()) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
        label = ""
    )
    val borderColor = animateColorAsState(
        targetValue = if (isError) MaterialTheme.colorScheme.error else if (isFocused) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
        label = ""
    )
    val trailingIconColor = animateColorAsState(
        targetValue = if (isError) MaterialTheme.colorScheme.error else if (isFocused) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
        label = ""
    )
    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            textStyle = textStyle,
            label = {
                Text(
                    text = label,
                    style = MaterialTheme.typography.titleMedium,
                    color = labelColor.value
                )
            },
            singleLine = true,
            enabled = isEnabled,
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            keyboardOptions = KeyboardOptions(
                imeAction = keyboardAction, keyboardType = keyboardType
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = borderColor.value,
                unfocusedBorderColor = borderColor.value,
                errorBorderColor = borderColor.value,
                focusedLabelColor = labelColor.value,
                unfocusedLabelColor = labelColor.value,
                errorLabelColor = labelColor.value,
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedTrailingIconColor = trailingIconColor.value,
                unfocusedTrailingIconColor = trailingIconColor.value
            ),
            isError = isError

        )
    }
}