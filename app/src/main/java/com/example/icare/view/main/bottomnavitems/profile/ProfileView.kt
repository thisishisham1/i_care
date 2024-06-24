package com.example.icare.view.main.bottomnavitems.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.icare.R
import com.example.icare.core.Dimens
import com.example.icare.model.sharedPreferences.PreferencesHelper
import com.example.icare.viewmodel.main.bottomnavitems.profile.ProfileViewModel

data class UserProfile(
    val name: String, val email: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController, preferencesHelper: PreferencesHelper) {
    val profileViewModel = remember {
        ProfileViewModel(navController, preferencesHelper)
    }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Profile", style = MaterialTheme.typography.titleLarge
                )
            }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        )
    }) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding) // Use Scaffold padding
                .padding(horizontal = Dimens.mediumPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(Dimens.largePadding))
            ProfileImage()
            Spacer(modifier = Modifier.height(Dimens.mediumPadding))
            Info(profileViewModel.userInfo)
            Spacer(modifier = Modifier.height(Dimens.largePadding))
            Content(profileViewModel)
        }
    }

    if (profileViewModel.isDialog.value) {
        LogoutDialog(onClickCancel = { profileViewModel.handleCancelDialog() },
            onClickLogout = { profileViewModel.handleLogoutDialog() })
    }
}

@Composable
private fun Info(userProfile: UserProfile) {
    Column(
        Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = userProfile.name, style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = userProfile.email,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ProfileImage() {

    Box(
        modifier = Modifier.size(180.dp)
    ) {
        Box {
            AsyncImage(
                model = R.drawable.d,
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(170.dp)
                    .clip(CircleShape)
            )
            Image(painter = painterResource(id = R.drawable.message_edit),
                contentDescription = "",
                modifier = Modifier
                    .padding(5.dp)
                    .size(30.dp)
                    .align(Alignment.BottomEnd)
                    .clickable {
                        // TODO: handle button edit image
                    })
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
        Modifier.fillMaxWidth()
    ) {
        uiList.forEach { pair ->
            ProfileItem(icon = pair.second,
                text = pair.first,
                onClick = { profileViewModel.handleClickAction(pair.first) })
        }
    }
}

@Composable
fun ProfileItem(icon: Int, text: String, onClick: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }
        .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.weight(1f))
        if (text != stringResource(id = R.string.profile_logout)) {
            Icon(
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
private fun LogoutDialog(onClickLogout: () -> Unit, onClickCancel: () -> Unit) {
    AlertDialog(onDismissRequest = { onClickCancel() },
        title = { Text(stringResource(id = R.string.profile_logout)) },
        text = { Text(stringResource(id = R.string.profile_dialog_agree)) },
        confirmButton = {
            Button(onClick = onClickLogout) {
                Text(stringResource(id = R.string.profile_logout))
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onClickCancel) {
                Text(stringResource(id = R.string.profile_cancel))
            }
        },
        containerColor = MaterialTheme.colorScheme.surface
    )
}