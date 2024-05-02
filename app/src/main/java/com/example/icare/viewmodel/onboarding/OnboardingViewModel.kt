package com.example.icare.viewmodel.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.icare.core.util.Destinations
import com.example.icare.model.sharedPreferences.PreferencesHelper

class OnboardingViewModel(
    private val navController: NavController,
    private val preferencesHelper: PreferencesHelper
) : ViewModel() {
    @OptIn(ExperimentalFoundationApi::class)
    suspend fun clickButton(
        pagerState: PagerState,
    ) {
        if (pagerState.currentPage == 3) {
            preferencesHelper.saveBooleanValue("onBoarding", true)
            navController.popBackStack()
            navController.navigate(Destinations.Login.route)
        } else {
            pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
        }

    }
}