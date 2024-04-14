package com.example.icare.presentation.mainscreen.screens.home.category.chatbot

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.icare.core.util.reusablecomponent.DefaultMessageInput
import com.example.icare.core.util.reusablecomponent.DefaultTopAppBar
import com.example.icare.core.util.reusablecomponent.MessageList

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatBot(navController: NavController) {
    val chatBotViewModel: ChatBotViewModel = viewModel()
    Scaffold(topBar = {
        DefaultTopAppBar(title = "Chat bot", navController = navController)
    }, bottomBar = {
        DefaultMessageInput(
            onAttachmentClicked = chatBotViewModel::handleAttachmentButton,
            onSend = chatBotViewModel::handleSendClick,
            onValueChange = chatBotViewModel::onValueChange
        )
    }) {
        MessageList(messages = chatBotViewModel.messages)
    }
}