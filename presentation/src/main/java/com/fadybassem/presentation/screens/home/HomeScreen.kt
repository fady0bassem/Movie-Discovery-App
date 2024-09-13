package com.fadybassem.presentation.screens.home

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.fadybassem.util.ObserveLifecycleEvents
import com.fadybassem.util.showToastMessage

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    viewModel.ObserveLifecycleEvents(LocalLifecycleOwner.current.lifecycle)

    val context = LocalContext.current
    val activity = context as Activity

    val popularMovies = viewModel.popularMovies.collectAsState()

    val showApiError = viewModel.showApiError

    Box(modifier = Modifier.fillMaxSize()) {
        HomeView(popularMovies = popularMovies)

        // show api error toast
        if (showApiError.value.first) {
            context.showToastMessage(showApiError.value.second)
            viewModel.showApiError.value = Pair(false, null)
        }
    }
}