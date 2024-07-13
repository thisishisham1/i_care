package com.example.icare.viewmodel.main.bottomnavitems.home

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.icare.MyApplication
import com.example.icare.model.classes.Destinations
import com.example.icare.model.classes.apiClass.UserResponse
import com.example.icare.model.local.UserDatabase
import kotlinx.coroutines.launch

class
HomeViewModel(val navController: NavController) : ViewModel() {

    fun handleNavigationDetail(userId: Int) {
        viewModelScope.launch {
            navController.navigate("${Destinations.Details.UserDetails.route}/${userId}")
        }
    }

    private val userDao =
        UserDatabase.getDatabase(MyApplication.applicationContext()).userResponseDao()

    val user: LiveData<UserResponse> = liveData {
        emit(userDao.getUser())
    }

    fun handleClickAction(action: String) {
        when (action) {
            "Personality Test" -> {
                val url = Uri.encode("https://icarembti.streamlit.app/")
                val title = Uri.encode("Personality Test")
                navController.navigate("${Destinations.WebView.WebViewScreen.route}/$url/$title")
            }

            "Chat Bot" -> {
                navController.navigate(Destinations.Chat.ChatBot.route)
            }

            "Labs" -> {
                navController.navigate(Destinations.Lists.Labs.route)
            }

            "Pharmacies" -> {
                navController.navigate(Destinations.Lists.Pharmacies.route)
            }

            "ECG Scan" -> {

                val url = Uri.encode("https://icareecgscan.streamlit.app/")
                val title = Uri.encode("ECG Scan")
                navController.navigate("${Destinations.WebView.WebViewScreen.route}/$url/$title")

            }

            "Medical Imaging" -> {
                val url = Uri.encode("http://icarescan2.streamlit.app")
                val title = Uri.encode("Medical Imaging")
                navController.navigate("${Destinations.WebView.WebViewScreen.route}/$url/$title")
            }
        }
    }

    fun handleClickSeeMore() = navController.navigate(Destinations.Lists.Doctors.route)

    fun handleNotificationClick() {
        navController.navigate(Destinations.Profile.Notifications.route)
    }

    fun handleChatsClick() {
        navController.navigate(Destinations.Lists.Chats.route)
    }
}