package com.example.icare.presentation.onboarding

import NewButton
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
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
import com.example.icare.presentation.Destinations
import com.example.icare.presentation.Dimens.mediumPadding
import com.example.icare.presentation.onboarding.component.OnBoardingPage
import com.example.icare.presentation.reusablecomponent.PageIndicator
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(navController: NavController) {
    val scopeCouroutine = rememberCoroutineScope()
    Column(modifier = Modifier.fillMaxSize()) {
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
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = mediumPadding)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PageIndicator(pageSize = pages.size, selectedPage = pagerState.currentPage)
            NewButton(text = buttonState.value) {
                scopeCouroutine.launch {
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
