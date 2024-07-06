package com.example.icare

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.icare.model.classes.apiClass.UsersResponse
import com.example.icare.model.local.UserDatabase
import com.example.icare.repository.UsersRepository
import kotlinx.coroutines.launch

class MainViewModel(private val usersRepository: UsersRepository) : ViewModel() {
    val pharmacies: MutableLiveData<List<UsersResponse>> = MutableLiveData()
    val labs: MutableLiveData<List<UsersResponse>> = MutableLiveData()
    val clinics: MutableLiveData<List<UsersResponse>> = MutableLiveData()

    val isClinicLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isPharmacyLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLabsLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        fetchClinics()
        fetchLabs()
        fetchPharmacies()
    }

    fun fetchClinics() {
        viewModelScope.launch {
            isClinicLoading.value = true
            val result = usersRepository.getClinics()
            result.onSuccess { clinicsList ->
                clinics.value = clinicsList

            }.onFailure { error ->
                Log.e("MainViewModel", "Error fetching clinics: ${error.message}")
            }
            isClinicLoading.value = false
        }
    }


    fun fetchPharmacies() {
        viewModelScope.launch {
            isPharmacyLoading.value = true
            val result = usersRepository.getPharmacies()
            result.onSuccess { pharmacyList ->
                pharmacies.value = pharmacyList
                Log.d("MainViewModel", "s fetching pharmacy:${pharmacyList.size}")

            }.onFailure {
                Log.d("MainViewModel", "Error fetching pharmacy:${it.message}")
            }
            isPharmacyLoading.value = false
        }
    }

    fun fetchLabs() {
        viewModelScope.launch {
            isLabsLoading.value = true
            val result = usersRepository.getLabs()
            result.onSuccess { labsList ->
                labs.value = labsList
            }.onFailure {
                Log.d("MainViewModel", "Error fetching labs:${it.message}")
            }
            isLabsLoading.value = false

        }

    }

    val userDao = UserDatabase.getDatabase(MyApplication.applicationContext()).userResponseDao()
    fun fetchReservation() {
        viewModelScope.launch {
            val result = usersRepository.getClinicReservation(userDao.getUser().id)
            result.onSuccess { reservationsList ->

                Log.d(
                    "MainViewModel",
                    "Fetched reservations: $reservationsList"
                ) // Log fetched reservations
            }.onFailure {
                Log.d("MainViewModel", "Error fetching Reservation:${it.message}")
            }
        }
    }
}