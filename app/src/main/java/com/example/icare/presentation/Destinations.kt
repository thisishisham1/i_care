package com.example.icare.presentation

sealed class Destinations(val route: String) {
    object OnBoarding : Destinations("OnBoarding")
    object HomeScreen:Destinations("Home Screen")
}