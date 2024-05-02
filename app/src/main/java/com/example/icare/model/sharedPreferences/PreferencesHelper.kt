package com.example.icare.model.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class PreferencesHelper(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("i care", Context.MODE_PRIVATE)

    fun saveStringValue(key: String, value: String) {
        sharedPreferences.edit {
            putString(key, value)
        }
    }

    fun saveIntValue(key: String, value: Int) {
        sharedPreferences.edit {
            putInt(key, value)
        }
    }

    fun saveBooleanValue(key: String, value: Boolean) {
        sharedPreferences.edit {
            putBoolean(key, value)
        }
    }

    fun getStringValue(keyString: String): String {
        return sharedPreferences.getString(keyString, "") ?: ""
    }

    fun getIntValue(keyInt: String): Int {
        return sharedPreferences.getInt(keyInt, 0)
    }

    fun getBooleanValue(keyBool: String): Boolean {
        return sharedPreferences.getBoolean(keyBool, false)
    }

}
