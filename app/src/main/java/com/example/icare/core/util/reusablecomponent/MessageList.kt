package com.example.icare.core.util.reusablecomponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.icare.core.theme.black
import com.example.icare.core.theme.gray400
import com.example.icare.core.theme.green500
import com.example.icare.core.theme.neutralWhite
import com.example.icare.core.util.Dimens
import com.example.icare.model.classes.Message

@Composable
private fun MessageBubble(
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
                .widthIn(max = 300.dp)
                .padding(8.dp),
        ) {
            Text(
                text = message.content,
                color = contentColor,
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.titleMedium,
            )
        }

    }
}

@Composable
fun MessageList(messages: List<Message>) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.mediumPadding, vertical = Dimens.smallPadding)
    ) {
        LazyColumn {
            items(messages) { message ->
                val isCurrentUser = message.senderId == "Me"
                MessageBubble(message = message, isCurrentUser = isCurrentUser)
            }
        }
    }
}