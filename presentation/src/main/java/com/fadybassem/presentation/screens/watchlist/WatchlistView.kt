package com.fadybassem.presentation.screens.watchlist

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fadybassem.domain.model.Movie
import com.fadybassem.presentation.components.screen_size.rememberWindowInfo
import com.fadybassem.presentation.dummy.DummyMovie
import com.fadybassem.presentation.screens.home.MovieItemView
import com.fadybassem.presentation.theme.AppTheme

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun WatchlistScreenPreview() {
    AppTheme {
        WatchlistView(
            watchlistMovies = remember { mutableStateOf(listOf(DummyMovie.movie)) },
            onMovieClick = {})
    }
}

@Composable
fun WatchlistView(watchlistMovies: State<List<Movie>>, onMovieClick: (Movie) -> Unit) {

    val windowInfo = rememberWindowInfo()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {

        item {
            Spacer(modifier = Modifier.padding(windowInfo.windowDimensions.verticalPadding))
        }

        items(watchlistMovies.value.size) { index ->
            val movie = watchlistMovies.value[index]

            Spacer(modifier = Modifier.padding(windowInfo.windowDimensions.verticalPadding))

            MovieItemView(movie = movie, onItemClick = {
                onMovieClick.invoke(it)
            })
        }

        item {
            Spacer(modifier = Modifier.padding(windowInfo.windowDimensions.verticalPadding))
        }
    }
}