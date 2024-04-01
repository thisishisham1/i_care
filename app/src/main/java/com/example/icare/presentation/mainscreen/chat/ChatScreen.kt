package com.example.icare.presentation.mainscreen.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Attachment
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.icare.core.theme.black
import com.example.icare.core.theme.gray400
import com.example.icare.core.theme.gray500
import com.example.icare.core.theme.green500
import com.example.icare.core.theme.neutralWhite
import com.example.icare.core.theme.shapes
import com.example.icare.core.util.Dimens
import com.example.icare.core.util.WidthSpacer
import com.example.icare.domain.model.Message

@Composable
fun ChatScreen(navController: NavController) {
    val chatViewModel = remember {
        ChatViewModel(navController = navController)
    }
    val value by chatViewModel.message.collectAsState()
    Scaffold(topBar = {
        TopAppBar(onClick = chatViewModel::handleArrowBack)
    }, bottomBar = {
        SendMessageInput(
            value = value,
            onchangeValue = chatViewModel::onChangeValue,
            onRecordClicked = chatViewModel::handleRecordClick,
            onCameraClicked = chatViewModel::handleCameraClick,
            onAttachmentClicked = chatViewModel::handleAttachmentClick, onSendClicked = chatViewModel::handleSendClick
        )
    }) {
        Box(modifier = Modifier.padding(it)) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = Dimens.mediumPadding, vertical = Dimens.smallPadding)
            ) {
                MessageList(messages = chatViewModel.messages)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar(onClick: () -> Unit) {
    androidx.compose.material3.TopAppBar(title = {
        Text(
            text = "Receiver",
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
    }, navigationIcon = {
        IconButton(onClick = { onClick() }) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }
    }, modifier = Modifier.fillMaxWidth())
}

@Composable
fun MessageBubble(
    message: Message,
    isCurrentUser: Boolean,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isCurrentUser) green500 else gray400.copy(alpha = 0.2f)
    val contentColor = if (isCurrentUser) neutralWhite else black
    Row(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = if (isCurrentUser) Arrangement.End else Arrangement.Start
    ) {
        Surface(
            shape = RoundedCornerShape(
                topStart = 12.dp,
                bottomEnd = 12.dp,
                bottomStart = if (!isCurrentUser) 0.dp else 12.dp,
                topEnd = if (isCurrentUser) 0.dp else 12.dp
            ),
            color = backgroundColor,
            modifier = Modifier
                .wrapContentHeight()
                .widthIn(max = 220.dp)
                .padding(8.dp),
        ) {
            Text(
                text = message.text,
                color = contentColor,
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.titleMedium,
            )
        }

    }
}

@Composable
fun MessageList(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            val isCurrentUser = message.sender == "Me"
            MessageBubble(message = message, isCurrentUser = isCurrentUser)
        }
    }
}

@Composable
fun SendMessageInput(
    modifier: Modifier = Modifier, value: String,
    onchangeValue: (String) -> Unit,
    onRecordClicked: () -> Unit,
    onCameraClicked: () -> Unit,
    onAttachmentClicked: () -> Unit,
    onSendClicked:()->Unit
) {
    val isArabic = remember(value) {
        value.any { Character.UnicodeBlock.of(it) == Character.UnicodeBlock.ARABIC }
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Surface(
            shadowElevation = 4.dp,
            shape = shapes.large,
            color = neutralWhite,
            modifier = Modifier.weight(0.7f)
        ) {
            OutlinedTextField(
                shape = shapes.large,
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledPlaceholderColor = gray500,
                    disabledTrailingIconColor = gray500,
                    focusedContainerColor = neutralWhite,
                    unfocusedContainerColor = neutralWhite,
                    unfocusedTextColor = black,
                    focusedTextColor = black
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    autoCorrect = true, imeAction = ImeAction.Send
                ),
                keyboardActions = KeyboardActions(onSend = {
                    // TODO: handle send button
                }),
                value = value,
                onValueChange = { onchangeValue(it) },
                modifier = Modifier
                    .padding(5.dp)
                    .height(IntrinsicSize.Min),
                textStyle = MaterialTheme.typography.titleMedium.copy(textAlign = if (isArabic) TextAlign.Right else TextAlign.Left),
                placeholder = {
                    Text(
                        text = "Type Message ...",
                        style = MaterialTheme.typography.titleMedium,
                    )
                },
                trailingIcon = {
                    Row {
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
                        if (value.isEmpty()){
                            IconButton(
                                onClick = { onCameraClicked() },
                                modifier = Modifier.padding(end = 4.dp),
                                content = {
                                    Icon(
                                        Icons.Filled.Camera,
                                        contentDescription = "Camera",
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            )
                        }
                    }
                }
            )
        }
        WidthSpacer(3.dp)
        if(value.isEmpty()){
            IconButton(
                onClick = { onRecordClicked() },
                modifier = Modifier
                    .height(IntrinsicSize.Min)
                    .clip(shape = CircleShape)
                    .background(green500)
                    .padding(8.dp)
                    .align(Alignment.CenterVertically),
                content = {
                    Icon(
                        Icons.Filled.Mic,
                        contentDescription = "Record",
                        modifier = Modifier.size(26.dp), tint = neutralWhite
                    )
                }
            )
        }else{
            IconButton(
                onClick = { onSendClicked() },
                modifier = Modifier
                    .height(IntrinsicSize.Min)
                    .clip(shape = CircleShape)
                    .background(green500)
                    .padding(8.dp)
                    .align(Alignment.CenterVertically),
                content = {
                    Icon(
                        Icons.AutoMirrored.Filled.Send,
                        contentDescription = "send",
                        modifier = Modifier.size(26.dp), tint = neutralWhite
                    )
                }
            )
        }

    }
}
