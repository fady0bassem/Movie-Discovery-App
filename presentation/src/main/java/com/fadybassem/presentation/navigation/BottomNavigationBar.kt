package com.fadybassem.presentation.navigation

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
import androidx.navigation.NavHostController
import com.fadybassem.presentation.R

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation (backgroundColor = Color.Black, contentColor = Color.White){
        BottomNavigationItem(icon = {
            Icon(
                Icons.Filled.Home,
                contentDescription = stringResource(id = R.string.content_description, MainRoutes.Home.route)
            )
        },
            label = { Text(stringResource(id = R.string.home)) },
            selected = currentRoute(navController) == MainRoutes.Home.route,
            onClick = {
                navController.navigate(MainRoutes.Home.route) {
                    popUpTo(MainRoutes.Home.route) { inclusive = true }
                }
            })
        BottomNavigationItem(icon = {
            Icon(
                Icons.AutoMirrored.Filled.ViewList,
                contentDescription = stringResource(id = R.string.content_description, MainRoutes.Watchlist.route)
            )
        },
            label = { Text(stringResource(id = R.string.watchlist)) },
            selected = currentRoute(navController) == MainRoutes.Watchlist.route,
            onClick = {
                navController.navigate(MainRoutes.Watchlist.route) {
                    popUpTo(MainRoutes.Home.route) { inclusive = false }
                }
            })
    }
}