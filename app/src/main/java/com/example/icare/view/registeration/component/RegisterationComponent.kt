package com.example.icare.view.registeration.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.icare.core.theme.black
import com.example.icare.core.theme.gray500
import com.example.icare.core.theme.green500
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


@Composable
fun CheckboxComponent(
    onTextSelected: (String) -> Unit,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        val checkedState = remember {
            mutableStateOf(false)
        }

        Checkbox(checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = !checkedState.value
                onCheckedChange.invoke(it)
            })

        ClickableTextComponent(onTextSelected)
    }
}

@Composable
fun ClickableTextComponent(onTextSelected: (String) -> Unit) {
    val initialText = "By continuing you accept our "
    val privacyPolicyText = "Privacy Policy"
    val andText = " and "
    val termsAndConditionsText = "Term of Use"

    val annotatedString = buildAnnotatedString {
        withStyle(MaterialTheme.typography.titleSmall.toSpanStyle()) {
            append(initialText)
        }

        withStyle(
            style = MaterialTheme.typography.titleSmall.copy(color = green500).toSpanStyle()
        ) {
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }
        withStyle(MaterialTheme.typography.titleSmall.toSpanStyle()) {
            append(andText)
        }
        withStyle(
            style = MaterialTheme.typography.titleSmall.copy(color = green500).toSpanStyle()
        ) {
            pushStringAnnotation(tag = termsAndConditionsText, annotation = termsAndConditionsText)
            append(termsAndConditionsText)
        }
    }

    ClickableText(text = annotatedString, onClick = { offset ->

        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.also { span ->
                Log.d("ClickableTextComponent", "{${span.item}}")
                if ((span.item == termsAndConditionsText) || (span.item == privacyPolicyText)) {
                    onTextSelected(span.item)
                }
            }

    })
}

