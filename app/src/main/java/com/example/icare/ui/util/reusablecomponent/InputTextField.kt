package com.example.icare.ui.util.reusablecomponent


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.icare.ui.theme.Shapes
import com.example.icare.ui.theme.neutralBlack
import com.example.icare.ui.theme.neutralGray
import com.example.icare.ui.theme.semanticRed

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
            .height(65.dp)
            .fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        textStyle = textStyle,
        label = {
            Text(text = label, style = MaterialTheme.typography.titleMedium)
        },
        shape = Shapes.medium,
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
            disabledLabelColor = neutralGray,
            errorLabelColor = semanticRed,
            focusedLabelColor = neutralBlack,
            disabledBorderColor = neutralGray,
            focusedBorderColor = neutralBlack,
            errorBorderColor = semanticRed,
            disabledContainerColor = neutralGray
        )
    )
}
