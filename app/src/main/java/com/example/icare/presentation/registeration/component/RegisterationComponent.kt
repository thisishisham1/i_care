package com.example.icare.presentation.registeration.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.icare.core.theme.black
import com.example.icare.core.theme.gray500
import com.example.icare.core.util.SizeImage

@Composable
fun ImageHeader(imageRes: Int, sizeImage: Dp = SizeImage) {
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageRes).crossfade(true).build(),
            contentDescription = "Image Header", Modifier.requiredSize(sizeImage)
        )
    }
}

@Composable
fun TextHeader(
    headerString: String,
    textAlign: TextAlign = TextAlign.Center,
    color: androidx.compose.ui.graphics.Color = black
) {
    Text(
        text = headerString,
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier
            .fillMaxWidth(),
        textAlign = textAlign,
        color = color
    )
}

@Composable
fun DescriptionHeader(text: String, textAlign: TextAlign = TextAlign.Center) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleSmall,
        color = gray500,
        modifier = Modifier.fillMaxWidth(),
        textAlign = textAlign
    )
}

