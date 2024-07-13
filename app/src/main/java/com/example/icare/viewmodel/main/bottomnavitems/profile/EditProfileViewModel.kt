package com.example.icare.viewmodel.main.bottomnavitems.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.navigation.NavController
import com.example.icare.MyApplication
import com.example.icare.model.classes.apiClass.UserResponse
import com.example.icare.model.local.UserDatabase

class EditProfileViewModel(val navController: NavController) : ViewModel() {
    val userDao = UserDatabase.getDatabase(MyApplication.applicationContext()).userResponseDao()
    val user: LiveData<UserResponse> = liveData {
        emit(userDao.getUser())
    }

    fun handleBackArrow() = navController.navigateUp()
}