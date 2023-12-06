package com.example.icare.presentation.onboarding

import NewButton
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.icare.presentation.Dimens.largePadding
import com.example.icare.presentation.Dimens.mediumPadding
import com.example.icare.presentation.onboarding.component.OnBoardingPage
import com.example.icare.presentation.reusablecomponent.PageIndicator

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen() {
    val pagerState = rememberPagerState(initialPage = 0) {
        pages.size
    }
    val buttonState = remember {
        derivedStateOf {
            if (pagerState.currentPage != 3) "Next" else "Get Started"
        }
    }
    HorizontalPager(state = pagerState) {
        index->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(mediumPadding)
        ) {
            OnBoardingPage(page = pages[index])
        }
    }
}

@Preview(showBackground = true)
@Composable
fun test() {
    OnBoardingScreen()
}