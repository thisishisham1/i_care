package com.example.icare.view.main.bottomnavitems.home.category.chatbot

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.icare.R
import com.example.icare.core.reusablecomponent.DefaultMessageInput
import com.example.icare.core.reusablecomponent.DefaultTopAppBar
import com.example.icare.core.reusablecomponent.MessageList
import com.example.icare.viewmodel.main.bottomnavitems.home.ChatBotViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatBotView(navController: NavController) {
    val chatBotViewModel: ChatBotViewModel = viewModel()
    val chatBotState by chatBotViewModel.chatBotState.collectAsState()
    Scaffold(topBar = {
        DefaultTopAppBar(title = "Chat Bot", navController = navController)
    }, bottomBar = {
        DefaultMessageInput(
            onSend = chatBotViewModel::onSendClicked,
            onValueChange = chatBotViewModel::onUserInputChanged
        )
    }) {
        if (chatBotState.messages.isEmpty()) Loader()
        else Box(modifier = Modifier.padding(top = 56.dp, bottom = 56.dp)) {
            MessageList(
                messages = chatBotState.messages, isBotTyping = chatBotState.isBotTyping
            )
        }
    }
}

@Composable
fun Loader() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.hi))
    LottieAnimation(
        composition = composition, iterations = Int.MAX_VALUE, reverseOnRepeat = true
    )
}