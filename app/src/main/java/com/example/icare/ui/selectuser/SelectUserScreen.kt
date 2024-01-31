package com.example.icare.ui.selectuser

import PrimaryButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.icare.R
import com.example.icare.model.Users
import com.example.icare.ui.selectuser.component.ImageSelectUser
import com.example.icare.ui.selectuser.component.SelectUserHeader
import com.example.icare.ui.selectuser.component.UserRadioGroup
import com.example.icare.util.Dimens
import com.example.icare.util.HeightSpacer

@Composable
fun SelectUserScreen() {
    val selectedOption = remember {
        mutableStateOf(Users.PATIENT)
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.largePadding, vertical = Dimens.mediumPadding)
            .verticalScroll(
                rememberScrollState()
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.smallPadding)
    ) {
        ImageSelectUser(selectUserImage)
        SelectUserHeader()
        HeightSpacer(Dimens.largePadding)
        UserRadioGroup(selectedOption)
        Spacer(modifier = Modifier.weight(1f))
        PrimaryButton(
            text = "Next",
            onClick = { /*TODO:Save user option */ },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

private val selectUserImage = R.drawable.selectuser