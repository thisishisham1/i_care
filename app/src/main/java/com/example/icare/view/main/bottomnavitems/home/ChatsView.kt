package com.example.icare.view.main.bottomnavitems.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.icare.core.reusablecomponent.DefaultTopAppBar

@Composable
fun ChatsView(navController: NavController) {
    Scaffold(topBar = { DefaultTopAppBar(title = "Chats", navController = navController) }) {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp)
        ) {
            items(3) { chat ->
                ChatCard(
                    chat = ChatData(
                        "https://img.freepik.com/free-photo/portrait-3d-male-doctor_23-2151107212.jpg?t=st=1720138180~exp=1720141780~hmac=f779d6ff4fc3965f73210d6d79a2a8575570752c96d67750498f2fb950df4954&w=740",
                        "Mohamed Ashmawy",
                        "hi"
                    )
                ) {
                }
            }
        }
    }
}

@Composable
fun ChatCard(chat: ChatData, onChatClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onChatClick() }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // User Image
        AsyncImage(
            model = chat.imageUrl,
            contentDescription = "User Image",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Chat Details
        Column(modifier = Modifier.weight(1f)) {
            Text(text = chat.name, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = chat.lastMessage,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

// Data class to hold chat information
data class ChatData(
    val imageUrl: String,
    val name: String,
    val lastMessage: String
)