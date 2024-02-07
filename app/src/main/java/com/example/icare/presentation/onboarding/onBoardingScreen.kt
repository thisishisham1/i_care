package com.example.icare.presentation.onboarding

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
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.icare.data.PreferencesHelper
import com.example.icare.presentation.onboarding.component.OnBoardingPage
import com.example.icare.core.util.Destinations
import com.example.icare.core.util.Dimens
import com.example.icare.presentation.onboarding.component.PageIndicator
import kotlinx.coroutines.launch
import com.example.icare.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(navController: NavController) {
    val context = LocalContext.current
    val preferencesHelper = PreferencesHelper(context)
    val scopeCoroutine = rememberCoroutineScope()
    val next = stringResource(id = R.string.next)
    val getStart = stringResource(id = R.string.get_started)
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
                if (pagerState.currentPage != 3) next else getStart
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
                            navController.navigate(Destinations.SignIn.route)
                        } else {
                            pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                        }
                    }
                },
            )
        }
    }
}
