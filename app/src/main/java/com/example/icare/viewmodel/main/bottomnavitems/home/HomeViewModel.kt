package com.example.icare.viewmodel.main.bottomnavitems.home

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.icare.model.classes.Destinations
import com.example.icare.model.classes.User

class HomeViewModel(val navController: NavController) : ViewModel() {
    fun handleNavigationDetail(user: User) {
        navController.navigate("${Destinations.Details.UserDetails.route}/${user.id}/${user.title}")
    }


    fun handleClickAction(action: String) {
        when (action) {
            "Personal Test" -> {
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