package com.example.icare.view.registeration.verify

import PrimaryButton
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.icare.R
import com.example.icare.core.util.Dimens
import com.example.icare.view.registeration.component.DescriptionHeader
import com.example.icare.view.registeration.component.TextHeader

@Composable
fun DoneVerifyView() {
    val emailVerified = stringResource(id = R.string.email_verified)
    val congratulation = stringResource(id = R.string.congratulations)
    val `continue` = stringResource(id = R.string.continue_)
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.largePadding, vertical = Dimens.mediumPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.largePadding)
    ) {
        CongratulationImage()
        TextHeader(headerString = emailVerified)
        DescriptionHeader(text = congratulation)
        Spacer(modifier = Modifier.weight(1f))
        PrimaryButton(text = `continue`, onClick = { /*TODO*/ })
    }
}

@Composable
private fun CongratulationImage() {
    var scale by remember {
        mutableFloatStateOf(0.2f)
    }
    LaunchedEffect(Unit) {
        scale = 1f
    }
    val animationScale by animateFloatAsState(
        targetValue = scale,
        label = "",
        animationSpec = tween(durationMillis = 1200)
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f), contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(R.drawable.circle).build(),
            contentDescription = "", modifier = Modifier.requiredSize(130.dp)
        )
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(R.drawable.circle2).build(),
            contentDescription = "", modifier = Modifier
                .scale(animationScale)
                .requiredSize(300.dp)
        )
    }
}