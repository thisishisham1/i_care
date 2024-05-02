package com.example.icare.view.main

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.icare.R
import com.example.icare.core.theme.gray500
import com.example.icare.core.theme.gray600
import com.example.icare.core.theme.green500
import com.example.icare.core.theme.green700
import com.example.icare.core.theme.neutralWhite

sealed class BottomNavItems(
    val label: Int,
    @DrawableRes val vectorIcon: Int
) {
    data object Home : BottomNavItems(
        label = R.string.home,
        vectorIcon = R.drawable.home
    )

    data object Search : BottomNavItems(
        label = R.string.search,
        vectorIcon = R.drawable.search
    )

    data object Appointment : BottomNavItems(
        label = R.string.appointment,
        vectorIcon = R.drawable.calendar
    )

    data object Profile : BottomNavItems(
        label = R.string.profile,
        vectorIcon = R.drawable.user
    )
}


@Composable
fun BottomNavBar(
    items: Array<BottomNavItems>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    NavigationBar(containerColor = neutralWhite, tonalElevation = 0.dp) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = { onItemSelected(index) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.vectorIcon),
                        contentDescription = "", Modifier.size(27.dp)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = neutralWhite,
                    selectedIconColor = green500,
                    selectedTextColor = green700,
                    unselectedIconColor = gray600, unselectedTextColor = gray500
                ),
                label = {
                    Text(
                        text = stringResource(id = item.label),
                        style = MaterialTheme.typography.titleSmall
                    )
                },
                alwaysShowLabel = false
            )
        }
    }
}


