package com.example.icare.view.main.bottomnavitems.profile.edit

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.icare.R
import com.example.icare.core.Dimens
import com.example.icare.core.reusablecomponent.HeightSpacer
import com.example.icare.core.theme.black
import com.example.icare.core.theme.gray400
import com.example.icare.core.theme.gray500
import com.example.icare.core.theme.shapes
import com.example.icare.viewmodel.main.bottomnavitems.profile.EditProfileViewModel

@Composable
fun EditProfileView(navController: NavController) {
    val editProfileViewModel = remember {
        EditProfileViewModel(navController)
    }
    Scaffold(topBar = {
        TopAppBar(editProfileViewModel = editProfileViewModel)
    }) {
        Box(Modifier.padding(it)) {
            Content(editProfileViewModel = editProfileViewModel)
        }
    }
}

@Composable
private fun ProfileImage() {
    Row(
        Modifier
            .fillMaxWidth()
            .height(intrinsicSize = IntrinsicSize.Min),
        horizontalArrangement = Arrangement.Center
    ) {
        Box {
            AsyncImage(
                model = R.drawable.d,
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(170.dp)
                    .clip(CircleShape)
            )
            Image(
                painter = painterResource(id = R.drawable.message_edit),
                contentDescription = "", modifier = Modifier
                    .padding(5.dp)
                    .size(30.dp)
                    .align(Alignment.BottomEnd)
                    .clickable {
                        // TODO: handle button edit image
                    }
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar(editProfileViewModel: EditProfileViewModel) {
    androidx.compose.material3.TopAppBar(title = {
        Text(
            text = "Edit Profile",
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineMedium,
        )
    }, navigationIcon = {
        IconButton(onClick = { editProfileViewModel.handleBackArrow() }) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }
    })
}

@Composable
fun TextFiled(editProfileViewModel: EditProfileViewModel) {
    val uiList = arrayOf(
        stringResource(id = R.string.name),
        stringResource(id = R.string.email),
    )
    Column(
        Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        uiList.forEachIndexed { _, filed ->
            when (filed) {
                "Name" ->
                    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                        Text(text = filed, style = MaterialTheme.typography.titleLarge)
                        TextField(
                            shape = shapes.medium,
                            value = editProfileViewModel.name.value,
                            onValueChange = { editProfileViewModel.name.value = it },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(), colors = TextFieldDefaults.colors(
                                focusedTextColor = black,
                                unfocusedContainerColor = gray400.copy(alpha = 0.2f),
                                focusedContainerColor = gray400.copy(alpha = 0.3f),
                                unfocusedTextColor = gray500,
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent
                            )
                        )
                    }

                else -> Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Text(text = filed, style = MaterialTheme.typography.titleLarge)
                    TextField(
                        isError = editProfileViewModel.isError.value,
                        shape = shapes.medium,
                        value = editProfileViewModel.email.value,
                        onValueChange = editProfileViewModel::onEmailValueChange,
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(), colors = TextFieldDefaults.colors(
                            focusedTextColor = black,
                            unfocusedContainerColor = gray400.copy(alpha = 0.2f),
                            focusedContainerColor = gray400.copy(alpha = 0.3f),
                            unfocusedTextColor = gray500,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        )
                    )
                    if (editProfileViewModel.isError.value) {
                        Text(
                            text = "Invalid email format",
                            color = Color.Red,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }

                }

            }
        }
    }
}

@Composable
private fun ResetPassword() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(50.dp)
            .clickable {
                // TODO: Implement reset password functionality
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.reset_password),
            style = MaterialTheme.typography.titleLarge,
            color = black
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = "",
            tint = black
        )

    }
}

@Composable
private fun Content(editProfileViewModel: EditProfileViewModel) {
    Column(
        Modifier
            .padding(horizontal = Dimens.mediumPadding, vertical = Dimens.smallPadding)
            .fillMaxSize()
    ) {
        ProfileImage()
        TextFiled(editProfileViewModel = editProfileViewModel)
        HeightSpacer(8.dp)
        ResetPassword()
    }
}