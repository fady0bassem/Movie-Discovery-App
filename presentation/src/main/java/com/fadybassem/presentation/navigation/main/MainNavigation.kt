package com.fadybassem.presentation.navigation.main

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fadybassem.core.R
import com.fadybassem.presentation.components.bottom_navigation_bar.BottomNavigationBar
import com.fadybassem.presentation.components.topbar.TopAppBarWithBackButton
import com.fadybassem.presentation.screens.details.DetailsScreen
import com.fadybassem.presentation.screens.home.HomeScreen
import com.fadybassem.presentation.screens.watchlist.WatchlistScreen
import com.fadybassem.presentation.theme.AppTheme
import com.fadybassem.util.MOVIE_ID

@Composable
fun MainNavigation(startDestination: String = MainRoutes.Home.route) {
    val activity = LocalContext.current as Activity

    val navController = rememberNavController()
    val bottomNavController = rememberNavController()

    var currentScreen by remember { mutableStateOf(startDestination) }

    AppTheme {
        Scaffold(topBar = {
            TopAppBarWithBackButton(
                navController = navController, title = when (currentRoute(navController)) {
                    MainRoutes.Home.route -> stringResource(id = R.string.home)
                    MainRoutes.Watchlist.route -> stringResource(id = R.string.watchlist)
                    MainRoutes.Details.route -> stringResource(id = R.string.movie_details)
                    else -> stringResource(id = R.string.app_name)
                }, showNavigationIcon = currentRoute(navController) != MainRoutes.Home.route
            )
        }, bottomBar = {
            when (currentRoute(navController)) {
                MainRoutes.Details.route -> null
                else -> BottomNavigationBar(navController = navController)
            }
        }) { innerPadding ->
            NavHost(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
                startDestination = startDestination
            ) {
                composable(route = MainRoutes.Home.route) {
                    currentScreen = MainRoutes.Home.route
                    HomeScreen(navController = navController)
                }

                composable(route = MainRoutes.Watchlist.route) {
                    currentScreen = MainRoutes.Watchlist.route
                    WatchlistScreen(navController = navController)
                }

                composable(
                    route = MainRoutes.Details.route + "/{$MOVIE_ID}", arguments = listOf(
                        navArgument(name = MOVIE_ID, builder = { type = NavType.IntType })
                    )
                ) {
                    currentScreen = MainRoutes.Details.route
                    DetailsScreen(navController = navController)
                }
            }
        }
    }

    BackHandler {
        when (navController.currentDestination?.route) {
            MainRoutes.Home.route -> {
                activity.finish()
            }

            else -> {
                navController.navigateUp()
            }
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}