package com.example.icare.viewmodel.main.bottomnavitems.home

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.navigation.NavController
import com.example.icare.MyApplication
import com.example.icare.model.classes.Destinations
import com.example.icare.model.classes.User
import com.example.icare.model.classes.UserResponse
import com.example.icare.model.local.UserDatabase

class
HomeViewModel(val navController: NavController) : ViewModel() {
    fun handleNavigationDetail(user: User) {
        navController.navigate("${Destinations.Details.UserDetails.route}/${user.id}/${user.title}")
    }

    private val userDao =
        UserDatabase.getDatabase(MyApplication.applicationContext()).userResponseDao()

    val user: LiveData<UserResponse> = liveData {
        emit(userDao.getUser())
    }

    fun handleClickAction(action: String) {
        when (action) {
            "Personality Test" -> {
                navController.navigate("${Destinations.WebView.WebViewScreen.route}/${Uri.encode("https://icarembti.streamlit.app/")}")
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
                navController.navigate(Destinations.Chat.EcgScanner.route)
            }

            "Medical Imaging" -> {
                navController.navigate(Destinations.Chat.CognitiveImaging.route)
            }
        }
    }

    fun handleClickSeeMore() = navController.navigate(Destinations.Lists.Doctors.route)

    fun handleNotificationClick() {
        navController.navigate(Destinations.Profile.Notifications.route)
    }

    fun handleChatsClick() {
        // TODO: handle chat Click
    }
}