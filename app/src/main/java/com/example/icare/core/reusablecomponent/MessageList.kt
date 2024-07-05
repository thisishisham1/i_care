package com.example.icare.core.reusablecomponent

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.icare.core.Dimens
import com.example.icare.core.theme.black
import com.example.icare.core.theme.gray400
import com.example.icare.core.theme.green500
import com.example.icare.core.theme.neutralWhite
import com.example.icare.model.classes.Message

@Composable
private fun MessageBubble(
    message: Message,
    modifier: Modifier = Modifier,
    isCurrentUser: Boolean
) {
    val (bubbleBackgroundColor, bubbleTextColor) = if (isCurrentUser) {
        Pair(green500, neutralWhite)
    } else {
        Pair(gray400.copy(alpha = 0.2f), black)
    }

    Row(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = if (isCurrentUser) Arrangement.End else Arrangement.Start
    ) {
        Surface(
            shape = RoundedCornerShape(12.dp).copy(
                bottomStart = if (isCurrentUser) CornerSize(12.dp) else CornerSize(0.dp), // Simplified conditional
                topEnd = if (isCurrentUser) CornerSize(0.dp) else CornerSize(12.dp)
            ),
            color = bubbleBackgroundColor,
            modifier = Modifier
                .wrapContentHeight()
                .widthIn(max = 300.dp)
                .padding(8.dp),
        ) {

            Text(
                text = message.content,
                color = bubbleTextColor,
                modifier = Modifier
                    .padding(8.dp)
                    .animateContentSize(),
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

@Composable
fun MessageList(messages: List<Message>, isBotTyping: Boolean = false) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.mediumPadding, vertical = Dimens.smallPadding)
    ) {
        LazyColumn {
            items(messages) { message ->
                val isCurrentUser = message.senderId == "Me"
                MessageBubble(
                    message = message, isCurrentUser = isCurrentUser
                )
            }
            item {
                AnimatedVisibility(visible = isBotTyping) {
                    Text(
                        text = "Bot is typing...",
                        modifier = Modifier.animateContentSize() // This will animate the size change
                    )
                }
            }
        }
    }
}


@Composable
private fun MedicalMessageBubble(
    message: MedicalMessage,
    modifier: Modifier = Modifier,
    isCurrentUser: Boolean
) {
    val backgroundColor = if (isCurrentUser) green500 else gray400.copy(alpha = 0.2f)
    val textColor = if (isCurrentUser) neutralWhite else black

    Row(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = if (isCurrentUser) Arrangement.End else Arrangement.Start
    ) {
        // Display image if sent by user
        if (isCurrentUser && message.imageUri != null) {
            AsyncImage(
                model = message.imageUri,
                contentDescription = "Sent Image",
                modifier = Modifier
                    .size(200.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            )
        } else {
            Surface(
                shape = RoundedCornerShape(12.dp).copy(
                    bottomStart = if (isCurrentUser) CornerSize(12.dp) else CornerSize(0.dp),
                    topEnd = if (isCurrentUser) CornerSize(0.dp) else CornerSize(12.dp)
                ),
                color = backgroundColor,
                modifier = Modifier
                    .wrapContentHeight()
                    .widthIn(max = 300.dp)
                    .padding(8.dp)
            ) {
                Text(
                    text = message.content,
                    color = textColor,
                    modifier = Modifier
                        .padding(8.dp)
                        .animateContentSize(),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
fun MedicalMessageList(messages: List<MedicalMessage>, isBotTyping: Boolean = false) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.mediumPadding, vertical = Dimens.smallPadding)
    ) {
        LazyColumn(reverseLayout = true) { // Reverse layout to show latest messages at the bottom
            items(messages.reversed()) { message -> // Reverse the list for display
                val isCurrentUser = message.senderId == "Me"
                MedicalMessageBubble(message = message, isCurrentUser = isCurrentUser)
            }
            item {
                AnimatedVisibility(visible = isBotTyping) {
                    Text(
                        text = "Bot is typing...",
                        modifier = Modifier.animateContentSize()
                    )
                }
            }
        }
    }
}

data class MedicalMessage(
    val content: String,
    val senderId: String,
    val imageUri: String? = null // Optional URI for images
)