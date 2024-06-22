package com.example.icare.model.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class PreferencesHelper(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("i care", Context.MODE_PRIVATE)

    fun saveUserLogin(token: String?) {
        sharedPreferences.edit {
            if (token != null) putString("userToken", token) else remove("userToken")
        }
    }

    fun isUserLoggedIn(): Boolean {
        val userToken = sharedPreferences.getString("userToken", null)
        return userToken != null
    }

    fun saveBooleanValue(key: String, value: Boolean) {
        sharedPreferences.edit {
            putBoolean(key, value)
        }
    }

    fun getBooleanValue(keyBool: String): Boolean {
        return sharedPreferences.getBoolean(keyBool, false)
    }

}
