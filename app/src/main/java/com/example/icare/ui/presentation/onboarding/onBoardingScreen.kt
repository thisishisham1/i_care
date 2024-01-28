package com.example.icare.ui.presentation.onboarding

import NewButton
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
import androidx.navigation.NavController
import com.example.icare.ui.presentation.onboarding.component.OnBoardingPage
import com.example.icare.ui.util.Destinations
import com.example.icare.ui.util.Dimens
import com.example.icare.ui.util.reusablecomponent.PageIndicator
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(navController: NavController) {
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
            OnBoardingPage(page = pages[index], navController = navController)
        }
        Spacer(modifier = Modifier.padding(Dimens.mediumPadding))
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            PageIndicator(pageSize = pages.size, selectedPage = pagerState.currentPage)
            NewButton(text = buttonState.value) {
                scopeCoroutine.launch {
                    if (pagerState.currentPage == 3) {
                        navController.popBackStack()
                        navController.navigate(Destinations.HomeScreen.route)
                    } else {
                        pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                    }
                }
            }
        }
    }

}
