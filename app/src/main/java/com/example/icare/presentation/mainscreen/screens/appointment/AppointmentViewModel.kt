package com.example.icare.presentation.mainscreen.screens.appointment

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppointmentViewModel : ViewModel() {
    private val _indexTab = MutableStateFlow(0)
    val indexTab = _indexTab.asStateFlow()
    fun onChangeTab(newIndex: Int) {
        _indexTab.value = newIndex
    }

    fun handleDoctorClick() {
        // TODO: handle doctor click
    }

    fun handleCompleteClick() {
        // TODO: handle complete click
    }

    fun handleCancelClick() {
        // TODO: handle cancel click
    }
}