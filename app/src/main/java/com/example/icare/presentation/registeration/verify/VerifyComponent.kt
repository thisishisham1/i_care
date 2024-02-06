package com.example.icare.presentation.registeration.verify

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.icare.R
import com.example.icare.core.theme.gray400
import com.example.icare.core.theme.shapes

@Composable
fun VerifyFiled() {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        (1..4).forEach {
            VerifyNumberContainer(number = it)
        }
    }

}

@Composable
fun VerifyNumberContainer(number: Int) {
    Box(
        modifier = Modifier
            .requiredSize(80.dp)
            .clip(shape = shapes.medium)
            .background(
                gray400.copy(alpha = 0.3f)
            ), contentAlignment = Alignment.Center
    ) {
        Text(
            text = number.toString(),
            style = MaterialTheme.typography.headlineLarge.copy(fontSize = 40.sp),
        )
    }
}

@Composable
fun CongratulationImage() {
    var scale by remember {
        mutableStateOf(0.2f)
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