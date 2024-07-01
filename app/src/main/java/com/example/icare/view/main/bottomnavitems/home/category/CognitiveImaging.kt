package com.example.icare.view.main.bottomnavitems.home.category

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.icare.core.reusablecomponent.DefaultTopAppBar
import com.example.icare.core.theme.neutralWhite

@Composable
fun CognitiveImaging(navController: NavController) {
    val filePickerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            println("Selected file: $uri")
        }
    Scaffold(topBar = {
        DefaultTopAppBar(
            title = "Medical Imaging", navController = navController
        )
    }, bottomBar = {
        BottomAppBar(containerColor = Color.Transparent, tonalElevation = 8.dp) {
            Spacer(Modifier.weight(1f))
            Button(
                onClick = { filePickerLauncher.launch("image/*") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Upload Image",
                    color = neutralWhite,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(Modifier.width(12.dp))
                Icon(
                    imageVector = Icons.Filled.FileUpload,
                    contentDescription = "Upload Image",
                    tint = neutralWhite
                )
            }

        }
    }) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(it)
        )
    }
}