package com.fadybassem.presentation.navigation

sealed class MainRoutes(val route: String) {
    data object Home : MainRoutes("home_screen")
    data object Watchlist : MainRoutes("watchlist_screen")
}