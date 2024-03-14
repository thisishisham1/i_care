package com.example.icare.presentation.mainscreen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.icare.R
import com.example.icare.core.theme.gray400
import com.example.icare.core.theme.gray500
import com.example.icare.core.theme.gray600
import com.example.icare.core.theme.green500
import com.example.icare.core.theme.green700
import com.example.icare.core.theme.neutralWhite

private sealed class BottomNavItems(
    val route: String,
    val label: String,
    @DrawableRes val vectorIcon: Int
) {
    data object Home : BottomNavItems(
        route = "Home",
        label = "Home",
        vectorIcon = R.drawable.home
    )

    data object Search : BottomNavItems(
        route = "Search",
        label = "Search",
        vectorIcon = R.drawable.search
    )

    data object Appointment : BottomNavItems(
        route = "Appointment",
        label = "Appointment",
        vectorIcon = R.drawable.calendar
    )

    data object Profile : BottomNavItems(
        route = "Profile",
        label = "Profile",
        vectorIcon = R.drawable.user
    )
}

private val _Destinations = arrayOf(
    BottomNavItems.Home,
    BottomNavItems.Search,
    BottomNavItems.Appointment, BottomNavItems.Profile
)

@Composable
fun BottomNavBar(navController: NavHostController) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination
    NavigationBar(containerColor = neutralWhite, tonalElevation = 0.dp) {
        _Destinations.forEach {
            AddItem(
                screen = it,
                currentDestinations = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
private fun RowScope.AddItem(
    screen: BottomNavItems,
    currentDestinations: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(selected =
    currentDestinations?.hierarchy?.any {
        it.route == screen.route
    } == true,
        onClick = { navController.navigate(screen.route) },
        icon = {
            Icon(
                painter = painterResource(id = screen.vectorIcon),
                contentDescription = "", Modifier.size(27.dp)
            )
        }, colors = NavigationBarItemDefaults.colors(
            indicatorColor = neutralWhite,
            selectedIconColor = green500,
            selectedTextColor = green700,
            unselectedIconColor = gray600, unselectedTextColor = gray500
        ), label = { Text(text = screen.label, style = MaterialTheme.typography.titleSmall) },
        alwaysShowLabel = false
    )
}
