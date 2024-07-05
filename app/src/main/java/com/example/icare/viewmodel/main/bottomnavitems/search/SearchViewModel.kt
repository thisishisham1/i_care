package com.example.icare.viewmodel.main.bottomnavitems.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.icare.MainViewModel
import com.example.icare.model.classes.Destinations
import com.example.icare.model.classes.UsersJson
import com.example.icare.model.classes.doesMatchQuery
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SearchViewModel(val navController: NavController, private val mainViewModel: MainViewModel) :
    ViewModel() {
    private val _indexTab = MutableStateFlow(0)
    val indexTab = _indexTab.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()


    private val _dataList = MutableStateFlow<List<UsersJson>>(emptyList())
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        Log.d("SearchViewModel", "Initializing and fetching initial data")
        mainViewModel.fetchClinics()
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            _isLoading.value = true
            _dataList.value = when (_indexTab.value) {
                0 -> mainViewModel.clinics.value ?: emptyList()
                1 -> mainViewModel.pharmacies.value ?: emptyList()
                else -> mainViewModel.labs.value ?: emptyList()
            }
            _isLoading.value = false
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
        Log.d("SearchViewModel", "Search text changed: $text")
        filterData()
    }

    fun onTabChange(newIndex: Int) {
        _indexTab.value = newIndex
        Log.d("SearchViewModel", "Tab changed to: $newIndex")
        fetchData()
    }

    fun handleClickItem(user: UsersJson) {
        navController.navigate("${Destinations.Details.UserDetails.route}/${user.id}/${user.first_name}")
    }

    private fun filterData() {
        viewModelScope.launch {
            val filtered = _searchText.value.let { text ->
                if (text.isBlank()) {
                    _dataList.value
                } else {
                    _dataList.value.filter { it.doesMatchQuery(text) }
                }
            }
            _dataList.value = filtered
            Log.d("SearchViewModel", "Data filtered: ${_dataList.value.size} items match the query")
        }
    }

    val filteredList: StateFlow<List<UsersJson>> = _searchText.combine(_dataList) { text, list ->
        if (text.isBlank()) {
            list
        } else {
            list.filter { it.doesMatchQuery(text) }
        }
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), initialValue = emptyList()
    )
}