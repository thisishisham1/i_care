package com.example.icare.view.main.chat

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.icare.core.reusablecomponent.DefaultMessageInput
import com.example.icare.core.reusablecomponent.DefaultTopAppBar
import com.example.icare.core.reusablecomponent.MessageList
import com.example.icare.viewmodel.main.chat.ChatViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatView(navController: NavController) {
    val chatViewModel = remember {
        ChatViewModel(navController = navController)
    }
    val messages = chatViewModel.messages
    Scaffold(topBar = {
        DefaultTopAppBar(title = "Dr Hisham Mohamed", navController = navController)
    }, bottomBar = {
        DefaultMessageInput(
            onAttachmentClicked = chatViewModel::handleAttachmentClick,
            onSend = chatViewModel::handleSendMessage,
            onValueChange = chatViewModel::onChangeValue
        )
    }) {
        MessageList(messages = messages)
    }
}

