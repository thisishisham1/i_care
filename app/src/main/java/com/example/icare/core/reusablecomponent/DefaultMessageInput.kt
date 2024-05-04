package com.example.icare.core.reusablecomponent

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Attachment
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.icare.core.Dimens
import com.example.icare.core.theme.black
import com.example.icare.core.theme.gray500
import com.example.icare.core.theme.green500
import com.example.icare.core.theme.neutralWhite
import com.example.icare.core.theme.shapes

@Composable
fun DefaultMessageInput(
    isHaveCameraButton: Boolean = false,
    onCameraClicked: () -> Unit = {},
    onAttachmentClicked: () -> Unit,
    onSend: (String) -> Unit,
    onValueChange: (String) -> Unit
) {
    val inputMessage = remember {
        mutableStateOf("")
    }
    Row(
        Modifier
            .background(neutralWhite)
            .padding(Dimens.mediumPadding)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        MessageTextFiled(inputMessage = inputMessage,
            onSend = { onSend(inputMessage.value) },
            onValueChange = { onValueChange(inputMessage.value) },
            onAttachmentClicked = { onAttachmentClicked() },
            isHaveCameraButton = isHaveCameraButton,
            onCameraClicked = { onCameraClicked() })
        IconButton(
            onClick = {
                onSend(inputMessage.value)
                inputMessage.value = ""
            },
            modifier = Modifier
                .clip(shape = CircleShape)
                .background(green500)
                .align(Alignment.Bottom),
            content = {
                Icon(
                    Icons.AutoMirrored.Filled.Send,
                    contentDescription = "send",
                    tint = neutralWhite
                )
            }
        )
    }
}

@Composable
private fun MessageTextFiled(
    isHaveCameraButton: Boolean = false,
    inputMessage: MutableState<String>,
    onSend: (String) -> Unit,
    onAttachmentClicked: () -> Unit,
    onValueChange: (String) -> Unit,
    onCameraClicked: () -> Unit = {}
) {
    OutlinedTextField(
        modifier = Modifier
            .heightIn(max = 150.dp, min = 55.dp)
            .fillMaxWidth(0.85f),
        value = inputMessage.value,
        onValueChange = {
            inputMessage.value = it
            onValueChange(inputMessage.value)
        },
        placeholder = {
            Text(
                text = "Message...",
                style = MaterialTheme.typography.titleMedium
            )
        },
        trailingIcon = {
            Row(modifier = Modifier.animateContentSize(animationSpec = tween(300))) {
                if (inputMessage.value.isEmpty()) {
                    if (isHaveCameraButton) {
                        CameraButton {
                            onCameraClicked()
                        }
                    }
                }
                AttachmentButton {
                    onAttachmentClicked()
                }

            }

        },
        shape = shapes.large,
        textStyle = MaterialTheme.typography.titleMedium,
        keyboardOptions = KeyboardOptions.Default.copy(
            autoCorrect = true, imeAction = ImeAction.Send
        ),
        keyboardActions = KeyboardActions(onSend = {
            onSend(inputMessage.value)
            inputMessage.value = ""
        }),
        colors = TextFieldDefaults.colors(
            cursorColor = black,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            disabledPlaceholderColor = gray500.copy(alpha = 0.4f),
            disabledTrailingIconColor = gray500.copy(alpha = 0.4f),
            focusedContainerColor = Color.Gray.copy(alpha = 0.25f),
            unfocusedContainerColor = Color.Gray.copy(alpha = 0.25f),
            unfocusedTextColor = black,
            focusedTextColor = black
        ),
    )
}

@Composable
private fun AttachmentButton(onAttachmentClicked: () -> Unit) {
    IconButton(
        onClick = { onAttachmentClicked() },
        modifier = Modifier.padding(end = 4.dp),
        content = {
            Icon(
                Icons.Filled.Attachment,
                contentDescription = "Attachment",
                modifier = Modifier.size(24.dp)
            )
        }
    )
}

@Composable
private fun CameraButton(onCameraClicked: () -> Unit) {
    IconButton(
        onClick = { onCameraClicked() },
        modifier = Modifier.padding(end = 4.dp),
        content = {
            Icon(
                Icons.Filled.PhotoCamera,
                contentDescription = "Attachment",
                modifier = Modifier.size(24.dp)
            )
        }
    )
}