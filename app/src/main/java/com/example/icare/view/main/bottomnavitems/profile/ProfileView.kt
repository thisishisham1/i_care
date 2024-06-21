package com.example.icare.view.main.bottomnavitems.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.icare.R
import com.example.icare.core.Dimens
import com.example.icare.core.reusablecomponent.HeightSpacer
import com.example.icare.core.reusablecomponent.WidthSpacer
import com.example.icare.core.theme.black
import com.example.icare.core.theme.blur
import com.example.icare.core.theme.gray500
import com.example.icare.core.theme.gray600
import com.example.icare.core.theme.neutralWhite
import com.example.icare.core.theme.shapes
import com.example.icare.model.sharedPreferences.PreferencesHelper
import com.example.icare.viewmodel.main.bottomnavitems.profile.ProfileViewModel

data class UserProfile(
    val name: String,
    val email: String
)

@Composable
fun ProfileScreen(navController: NavController, preferencesHelper: PreferencesHelper) {
    val profileViewModel = remember {
        ProfileViewModel(navController, preferencesHelper)
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.mediumPadding, vertical = Dimens.largePadding),
    ) {
        ProfileImage()
        Info(profileViewModel.userInfo)
        HeightSpacer(8.dp)
        Content(profileViewModel)
        if (profileViewModel.isDialog.value) LogoutDialog(onClickCancel = {
            profileViewModel.handleCancelDialog()
        }, onClickLogout = { profileViewModel.handleLogoutDialog() })
    }
}

@Composable
private fun Info(userProfile: UserProfile) {
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = userProfile.name, style = MaterialTheme.typography.titleMedium)
        Text(text = userProfile.email, style = MaterialTheme.typography.titleSmall, color = gray600)
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

@Composable
private fun Content(profileViewModel: ProfileViewModel) {
    val uiList = arrayOf(
        stringResource(id = R.string.profile_edit) to R.drawable.user_edit,
        stringResource(id = R.string.profile_settings) to R.drawable.setting,
        stringResource(id = R.string.profile_terms) to R.drawable.security_safe,
        stringResource(id = R.string.profile_logout) to R.drawable.logout
    )
    Column(
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        uiList.forEachIndexed { _, pair ->
            HeightSpacer(5.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        profileViewModel.handleClickAction(pair.first)
                    },
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = pair.second),
                    contentDescription = "",
                    tint = gray600
                )
                Text(
                    text = pair.first,
                    style = MaterialTheme.typography.titleLarge,
                    color = gray600
                )
                Spacer(modifier = Modifier.weight(1f))
                if (pair.first != "Log Out") {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = "",
                        tint = gray600
                    )
                }
            }
        }
    }
}

@Composable
private fun LogoutDialog(onClickLogout: () -> Unit, onClickCancel: () -> Unit) {
    Dialog(onDismissRequest = { }) {
        Column(
            Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                Modifier
                    .fillMaxWidth(0.9f)
                    .height(IntrinsicSize.Max)
                    .clip(shapes.medium)
                    .background(neutralWhite)
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.profile_logout),
                    style = MaterialTheme.typography.headlineMedium
                )
                HorizontalDivider(thickness = 1.dp)
                Text(
                    text = stringResource(id = R.string.profile_dialog_agree),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                    color = gray500
                )
                Buttons(onLogoutClick = { onClickLogout() }, onCancelClick = { onClickCancel() })
            }
        }
    }
}

@Composable
private fun Buttons(onLogoutClick: () -> Unit, onCancelClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = { onCancelClick() },
            Modifier
                .width(140.dp)
                .height(45.dp), colors = ButtonDefaults.buttonColors(
                containerColor = blur, contentColor = black
            )
        ) {
            Text(
                text = stringResource(id = R.string.profile_cancel),
                style = MaterialTheme.typography.titleMedium
            )
        }
        WidthSpacer(5.dp)
        Button(
            onClick = { onLogoutClick() },
            Modifier
                .width(140.dp)
                .height(45.dp)
        ) {
            Text(
                text = stringResource(id = R.string.profile_logout),
                style = MaterialTheme.typography.titleMedium
            )
        }

    }
}
