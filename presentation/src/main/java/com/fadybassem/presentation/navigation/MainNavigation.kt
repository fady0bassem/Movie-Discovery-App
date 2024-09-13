package com.fadybassem.presentation.navigation

import android.app.Activity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fadybassem.presentation.screens.home.HomeScreen
import com.fadybassem.presentation.screens.watchlist.WatchlistScreen
import com.fadybassem.presentation.theme.AppTheme

@Composable
fun MainNavigation(startDestination: String = MainRoutes.Home.route) {
    val activity = LocalContext.current as Activity

    val navController = rememberNavController()
    val bottomNavController = rememberNavController()

    var currentScreen by remember { mutableStateOf(startDestination) }

    AppTheme {
        Scaffold(topBar = {
            TopAppBarWithBackButton(navController = navController)
        }, bottomBar = {
            BottomNavigationBar(navController = navController)
        }) { innerPadding ->
            NavHost(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
                startDestination = startDestination
            ) {
                composable(MainRoutes.Home.route) {
                    HomeScreen(navController = navController)
                }

                composable(MainRoutes.Watchlist.route) {
                    WatchlistScreen()
                }
            }
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}
