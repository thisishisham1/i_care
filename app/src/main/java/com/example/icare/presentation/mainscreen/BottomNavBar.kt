package com.example.icare.presentation.mainscreen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.icare.R
import com.example.icare.core.theme.gray500
import com.example.icare.core.theme.gray600
import com.example.icare.core.theme.green500
import com.example.icare.core.theme.green700
import com.example.icare.core.theme.neutralWhite
import com.example.icare.core.util.Destinations

sealed class BottomNavItems(
    val label: String,
    @DrawableRes val vectorIcon: Int
) {
    data object Home : BottomNavItems(
        label = "Home",
        vectorIcon = R.drawable.home
    )

    data object Search : BottomNavItems(
        label = "Search",
        vectorIcon = R.drawable.search
    )

    data object Appointment : BottomNavItems(
        label = "Appointment",
        vectorIcon = R.drawable.calendar
    )

    data object Profile : BottomNavItems(
        label = "Profile",
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
                }, colors = NavigationBarItemDefaults.colors(
                    indicatorColor = neutralWhite,
                    selectedIconColor = green500,
                    selectedTextColor = green700,
                    unselectedIconColor = gray600, unselectedTextColor = gray500
                ), label = { Text(text = item.label, style = MaterialTheme.typography.titleSmall) },
                alwaysShowLabel = false
            )
        }
    }
}


