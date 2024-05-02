package com.example.icare.view.main.bottomnavitems.home.category.chatbot

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.icare.viewmodel.main.bottomnavitems.home.ChatBotViewModel

@Composable
fun ChatBotView() {
    val chatBotViewModel: ChatBotViewModel = viewModel()
    val message = chatBotViewModel.message.value
    Column(
        Modifier
            .fillMaxSize()
    ) {

    }
}
