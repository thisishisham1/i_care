package com.example.icare.view.onboarding

import PrimaryButton
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.icare.R
import com.example.icare.core.Dimens
import com.example.icare.model.classes.Destinations
import com.example.icare.model.sharedPreferences.PreferencesHelper
import com.example.icare.viewmodel.onboarding.OnboardingViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingView(navController: NavController) {
    val preferencesHelper = PreferencesHelper(LocalContext.current)
    val vm = remember {
        OnboardingViewModel(preferencesHelper = preferencesHelper, navController = navController)
    }
    val coroutineScope = rememberCoroutineScope()
    val next = stringResource(id = R.string.next)
    val getStart = stringResource(id = R.string.get_started)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.largePadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }
        val buttonState = remember {
            derivedStateOf {
                if (pagerState.currentPage != 3) next else getStart
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.mediumPadding),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.skip),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    preferencesHelper.saveBooleanValue("onBoarding", true)
                    navController.popBackStack()
                    navController.navigate(Destinations.Auth.Login.route)
                })
            Spacer(modifier = Modifier.width(Dimens.smallPadding))
            Icon(
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = "skip",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.requiredSize(18.dp)
            )
        }
        Spacer(modifier = Modifier.height(Dimens.largePadding))
        HorizontalPager(state = pagerState) { index ->
            OnBoardingPage(page = pages[index])
        }
        Spacer(modifier = Modifier.padding(Dimens.mediumPadding))
        PageIndicator(pageSize = pages.size, selectedPage = pagerState.currentPage)
        Spacer(modifier = Modifier.weight(1f))
        PrimaryButton(
            text = buttonState.value,
            onClick = {
                coroutineScope.launch {
                    vm.clickButton(pagerState)
                }
            },
        )

    }
}