package com.example.icare.presentation.mainscreen.screenDetails

import androidx.lifecycle.ViewModel
import com.example.icare.domain.model.listOfDoctor

class DetailsViewModel : ViewModel() {
    fun getDoctorDetails(doctorId: Int) = listOfDoctor.first { it.id == doctorId }
}