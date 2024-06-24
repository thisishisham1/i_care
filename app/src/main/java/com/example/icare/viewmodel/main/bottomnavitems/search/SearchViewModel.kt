package com.example.icare.viewmodel.main.bottomnavitems.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.icare.model.classes.Destinations
import com.example.icare.model.classes.User
import com.example.icare.model.classes.Users
import com.example.icare.model.classes.listOfDoctor
import com.example.icare.model.classes.listOfLabs
import com.example.icare.model.classes.listOfPharmacy
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


    private val _dataList = MutableStateFlow<List<Users>>(emptyList())
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

    fun handleClickItem(user: User) {
        navController.navigate("${Destinations.Details.UserDetails.route}/${user.id}/${user.title}")
    }

    val filteredList: StateFlow<List<Users>> = _searchText.combine(_dataList) { text, list ->
        if (text.isBlank()) {
            list
        } else {
            list.filter { it.doseMatchQuery(text) }
        }
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), initialValue = emptyList<Users>()
    )
}