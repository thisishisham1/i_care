package com.example.icare.presentation.selectuser.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.icare.R
import com.example.icare.domain.model.Users
import com.example.icare.core.theme.gray500
import com.example.icare.core.theme.green500
import com.example.icare.core.theme.shapes
import com.example.icare.core.util.Dimens
import com.example.icare.core.util.HeightSpacer
import com.example.icare.core.util.WidthSpacer

@Composable
fun ImageSelectUser(selectUserImage: Int) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current).crossfade(true).data(selectUserImage)
            .build(),
        contentDescription = "image select user", modifier = Modifier.requiredSize(
            300.dp
        )
    )
}

@Composable
fun SelectUserHeader() {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Tailor Your Experience",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
        HeightSpacer(Dimens.mediumPadding)
        Text(
            text = "To provide you  with good\nexperience, please select your role\nbelow:",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center, color = gray500
        )
    }
}

@Composable
fun UserRadioGroup(selectedOption: MutableState<Users>) {

    Column(
        verticalArrangement = Arrangement.spacedBy(Dimens.mediumPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = shapes.medium)
    ) {
        Users.entries.forEach { user ->
            RadioItem(option = user, selectedOption = selectedOption.value) {
                selectedOption.value = user
            }
        }
    }

}

@Composable
private fun RadioIcon(isSelected: Boolean, onClick: () -> Unit) {
    val on = painterResource(id = R.drawable.on)
    val off = painterResource(id = R.drawable.off)
    Icon(
        painter = if (isSelected) on else off,
        contentDescription = null,
        modifier = Modifier
            .size(24.dp)
            .clickable {
                onClick()
            },
        tint = if (isSelected) green500 else gray500
    )
}

@Composable
fun RadioItem(option: Users, selectedOption: Users, onOptionSelected: () -> Unit) {
    Row(
        Modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth(0.5f)
            .selectable(
                selected = (option == selectedOption),
                onClick = { onOptionSelected() }
            )
            .clip(shapes.medium),
        horizontalArrangement = Arrangement.Start
    ) {
        RadioIcon(
            isSelected = (option == selectedOption),
            onClick = { onOptionSelected() })
        WidthSpacer(Dimens.mediumPadding)
        Text(
            text = option.roleDescription,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
            color = if (option.roleDescription == selectedOption.roleDescription) green500 else gray500,
        )
    }

}
