package com.example.icare.viewmodel.main.details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.icare.MainViewModel
import com.example.icare.MyApplication
import com.example.icare.model.classes.apiClass.ReservationRequest
import com.example.icare.model.classes.apiClass.UserResponse
import com.example.icare.model.classes.apiClass.UsersResponse
import com.example.icare.model.local.UserDatabase
import com.example.icare.repository.UsersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(val navController: NavController, private val mainViewModel: MainViewModel) :
    ViewModel() {
    private val userDao =
        UserDatabase.getDatabase(MyApplication.applicationContext()).userResponseDao()

    private val _mainUser = MutableLiveData<UserResponse>()

    init {
        viewModelScope.launch {
            _mainUser.postValue(userDao.getUser())
        }
    }

    private val usersRepository = UsersRepository()
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _user = MutableStateFlow<UsersResponse?>(null)
    val user: StateFlow<UsersResponse?> = _user.asStateFlow()

    fun fetchUserDetails(userId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            Log.d("DetailsViewModel", "Fetching user details for userId: $userId")

            val fetchedUser = mainViewModel.clinics.value?.firstOrNull { it.id == userId }
                ?: mainViewModel.pharmacies.value?.firstOrNull { it.id == userId }
                ?: mainViewModel.labs.value?.firstOrNull { it.id == userId }

            _user.value = fetchedUser
            _isLoading.value = false
            Log.d("DetailsViewModel", "Finished fetching user details. User: $fetchedUser")
        }
    }

    fun handleBookButtonClick(user: UsersResponse) {
        viewModelScope.launch {
            val result = usersRepository.insertReservation(
                ReservationRequest(
                    clinic_id = user.id,
                    patient_id = _mainUser.value!!.id,
                    name = _mainUser.value!!.name1,
                    phone = _mainUser.value!!.phone
                )
            )
            result.onSuccess {
                Log.d("DetailsViewModel", "Success")
            }.onFailure { exception ->
                Log.e("DetailsViewModel", "filed : ${exception.message}")
            }
        }
    }

}