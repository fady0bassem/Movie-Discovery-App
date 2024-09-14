package com.fadybassem.presentation.navigation.main

sealed class MainRoutes(val route: String) {
    data object Home : MainRoutes("home_screen")
    data object Watchlist : MainRoutes("watchlist_screen")
    data object Details : MainRoutes("details_screen")
}