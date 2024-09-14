package com.fadybassem.presentation.components.bottom_navigation_bar

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ViewList
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.fadybassem.core.R
import com.fadybassem.presentation.navigation.main.MainRoutes
import com.fadybassem.presentation.navigation.main.currentRoute

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation(backgroundColor = Color.Black, contentColor = Color.White) {
        BottomNavigationItem(icon = {
            Icon(
                Icons.Filled.Home, contentDescription = stringResource(
                    id = R.string.content_description, MainRoutes.Home.route
                )
            )
        },
            label = { Text(stringResource(id = R.string.home)) },
            selected = currentRoute(navController) == MainRoutes.Home.route,
            onClick = {
                navController.navigate(MainRoutes.Home.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            })
        BottomNavigationItem(icon = {
            Icon(
                Icons.AutoMirrored.Filled.ViewList, contentDescription = stringResource(
                    id = R.string.content_description, MainRoutes.Watchlist.route
                )
            )
        },
            label = { Text(stringResource(id = R.string.watchlist)) },
            selected = currentRoute(navController) == MainRoutes.Watchlist.route,
            onClick = {
                navController.navigate(MainRoutes.Watchlist.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            })
    }
}