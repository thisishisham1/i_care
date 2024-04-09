package com.example.icare

import android.app.Application
import com.google.firebase.FirebaseApp

class Auth : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}