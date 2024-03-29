package com.example.icare.presentation.mainscreen.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.icare.core.util.Destinations
import com.example.icare.domain.model.Doctor
import com.example.icare.domain.model.Lab
import com.example.icare.domain.model.Pharmacy
import com.example.icare.domain.model.SearchItem
import com.example.icare.domain.model.listOfDoctor
import com.example.icare.domain.model.listOfLabs
import com.example.icare.domain.model.listOfPharmacy
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SearchViewModel(val navController: NavController) : ViewModel() {
    private val _indexTab = MutableStateFlow(0)
    val indexTab = _indexTab.asStateFlow()

    // state the text typed by the user
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()


    private val _dataList = MutableStateFlow<List<SearchItem>>(emptyList())
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            _isLoading.value = true
            delay(1000)
            _dataList.value = when (_indexTab.value) {
                0 -> listOfDoctor
                1 -> listOfPharmacy
                else -> listOfLabs
            }
            _isLoading.value = false
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
        fetchData()
    }

    fun onTabChange(newIndex: Int) {
        _indexTab.value = newIndex
        fetchData()
    }

    fun handleClickItem(item: SearchItem) {
        when (item) {
            is Doctor -> navController.navigate("${Destinations.DoctorDetails.route}/${item.id}")
            is Pharmacy -> navController.navigate("${Destinations.PharmacyDetails.route}/${item.id}")
            is Lab -> navController.navigate("${Destinations.LabDetails.route}/${item.id}")

        }
    }

    val filteredList: StateFlow<List<SearchItem>> = _searchText.combine(_dataList) { text, list ->
        if (text.isBlank()) {
            list
        } else {
            list.filter { it.doseMatchQuery(text) }
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList<SearchItem>()
    )
}