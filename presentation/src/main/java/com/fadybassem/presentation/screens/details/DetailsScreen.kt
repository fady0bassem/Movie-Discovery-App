package com.fadybassem.presentation.screens.details

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavHostController
import com.fadybassem.presentation.navigation.main.MainRoutes
import com.fadybassem.presentation.theme.AppTheme
import com.fadybassem.util.ObserveLifecycleEvents
import com.fadybassem.util.showToastMessage

@Composable
fun DetailsScreen(navController: NavHostController, viewModel: DetailsViewModel = hiltViewModel()) {
    viewModel.ObserveLifecycleEvents(LocalLifecycleOwner.current.lifecycle)

    val context = LocalContext.current
    val activity = context as Activity

    val apiStatus by remember { viewModel.apiStatus }

    val movieDetails = viewModel.movieDetails.collectAsState()
    val movieDetailsCredit = viewModel.movieDetailsCredit.collectAsState()
    val movieDetailsSimilar = viewModel.movieDetailsSimilar.collectAsState()

    val showApiError = viewModel.showApiError

    AppTheme(apiStatus = apiStatus) {
        Box(modifier = Modifier.fillMaxSize()) {
            DetailsView(
                movieDetails = movieDetails,
                movieDetailsCredit = movieDetailsCredit,
                movieDetailsSimilar = movieDetailsSimilar,
                onMovieClick = {
                    navController.navigate(MainRoutes.Details.route + "/${it.id}")
                }
            )

            // show api error toast
            if (showApiError.value.first) {
                context.showToastMessage(showApiError.value.second)
                viewModel.showApiError.value = Pair(false, null)
            }
        }
    }
}