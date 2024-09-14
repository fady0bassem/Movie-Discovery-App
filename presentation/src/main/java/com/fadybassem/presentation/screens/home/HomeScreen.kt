package com.fadybassem.presentation.screens.home

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.fadybassem.presentation.navigation.main.MainRoutes
import com.fadybassem.presentation.theme.AppTheme
import com.fadybassem.util.ObserveLifecycleEvents
import com.fadybassem.util.showToastMessage

@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {
    viewModel.ObserveLifecycleEvents(LocalLifecycleOwner.current.lifecycle)

    val context = LocalContext.current
    val activity = context as Activity

    val apiStatus by remember { viewModel.apiStatus }

    val popularMovies = viewModel.popularMovies.collectAsState()
    val moviesPageFlow = viewModel.moviesPageFlow.collectAsLazyPagingItems()

    val showApiError = viewModel.showApiError

    val popularMoviesListState = rememberSaveable(saver = LazyListState.Saver ) {
        LazyListState(
            firstVisibleItemIndex = viewModel.popularMoviesScrollIndex,
            firstVisibleItemScrollOffset = viewModel.popularMoviesScrollOffset
        )
    }

    val moviesListState = rememberSaveable(saver = LazyListState.Saver ) {
        LazyListState(
            firstVisibleItemIndex = viewModel.moviesScrollIndex,
            firstVisibleItemScrollOffset = viewModel.moviesScrollOffset
        )
    }

    AppTheme(apiStatus = apiStatus) {
        Box(modifier = Modifier.fillMaxSize()) {
            HomeView(popularMovies = popularMovies,
                moviesPageFlow = moviesPageFlow,
                popularMoviesListState = popularMoviesListState,
                moviesListState = moviesListState,
                onMovieClick = {
                    navController.navigate(MainRoutes.Details.route + "/${it.id}")
                })

            // show api error toast
            if (showApiError.value.first) {
                context.showToastMessage(showApiError.value.second)
                viewModel.showApiError.value = Pair(false, null)
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.popularMoviesScrollIndex = popularMoviesListState.firstVisibleItemIndex
            viewModel.popularMoviesScrollOffset = popularMoviesListState.firstVisibleItemScrollOffset
            viewModel.moviesScrollIndex = moviesListState.firstVisibleItemIndex
            viewModel.moviesScrollOffset = moviesListState.firstVisibleItemScrollOffset
        }
    }
}