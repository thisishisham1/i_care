package com.example.icare.presentation.mainscreen.chat

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.icare.core.util.reusablecomponent.DefaultMessageInput
import com.example.icare.core.util.reusablecomponent.DefaultTopAppBar
import com.example.icare.core.util.reusablecomponent.MessageList

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatScreen(navController: NavController) {
    val chatViewModel = remember {
        ChatViewModel(navController = navController)
    }
    val messages = chatViewModel.messages
    Scaffold(topBar = {
        DefaultTopAppBar(title = "Dr Hisham Mohamed", navController = navController)
    }, bottomBar = {
        DefaultMessageInput(
            isHaveCameraButton = true, onCameraClicked = chatViewModel::handleCameraClick,
            onAttachmentClicked = chatViewModel::handleAttachmentClick,
            onSend = chatViewModel::handelSendClick, onValueChange = chatViewModel::onChangeValue
        )
    }) {
        MessageList(messages = messages)
    }
}

