package com.example.icare.ui.presentation.onboarding

import PrimaryButton
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.icare.data.PreferencesHelper
import com.example.icare.ui.presentation.onboarding.component.OnBoardingPage
import com.example.icare.ui.util.Destinations
import com.example.icare.ui.util.Dimens
import com.example.icare.ui.presentation.onboarding.component.PageIndicator
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(navController: NavController) {
    val context = LocalContext.current
    val preferencesHelper = PreferencesHelper(context)
    val scopeCoroutine = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.largePadding)
    ) {
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }
        val buttonState = remember {
            derivedStateOf {
                if (pagerState.currentPage != 3) "Next" else "Get Started"
            }
        }
        HorizontalPager(state = pagerState) { index ->
            OnBoardingPage(
                page = pages[index],
                navController = navController,
                preferencesHelper = preferencesHelper
            )
        }
        Spacer(modifier = Modifier.padding(Dimens.mediumPadding))
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            PageIndicator(pageSize = pages.size, selectedPage = pagerState.currentPage)
            PrimaryButton(
                text = buttonState.value,
                onClick = {
                    scopeCoroutine.launch {
                        if (pagerState.currentPage == 3) {
                            preferencesHelper.saveBooleanValue("onBoarding", true)
                            navController.popBackStack()
                            navController.navigate(Destinations.SignUp.route)
                        } else {
                            pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                        }
                    }
                },
            )
        }
    }
}
