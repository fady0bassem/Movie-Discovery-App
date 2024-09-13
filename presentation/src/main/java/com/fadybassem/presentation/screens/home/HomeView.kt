package com.fadybassem.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.fadybassem.core.R
import com.fadybassem.domain.model.Movie
import com.fadybassem.presentation.components.screen_size.rememberWindowInfo
import com.fadybassem.presentation.theme.AppTheme

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    AppTheme {
        HomeView(popularMovies = remember {
            mutableStateOf(
                listOf(
                    Movie(
                        adult = false,
                        backdropPath = "/tbgIhYwQ5IAgNaFU1SBBxxNXCmm.jpg",
                        genreIds = arrayListOf(80, 28, 30),
                        id = 646097,
                        originalLanguage = "en",
                        originalTitle = "Rebel Ridge",
                        overview = "A former Marine confronts corruption in a small town when local law enforcement unjustly seizes the bag of cash he needs to post his cousin's bail.",
                        popularity = 831.205,
                        posterPath = "https://image.tmdb.org/t/p/w1280/8cdWjvZQUExUUTzyp4t6EDMubfO.jpg",
                        releaseDate = "2024-08-27",
                        title = "Rebel Ridge",
                        video = false,
                        voteAverage = 6.797,
                        voteCount = 317,
                    ), Movie(
                        adult = false,
                        backdropPath = "/tbgIhYwQ5IAgNaFU1SBBxxNXCmm.jpg",
                        genreIds = arrayListOf(80, 28, 30),
                        id = 646097,
                        originalLanguage = "en",
                        originalTitle = "Rebel Ridge",
                        overview = "A former Marine confronts corruption in a small town when local law enforcement unjustly seizes the bag of cash he needs to post his cousin's bail.",
                        popularity = 831.205,
                        posterPath = "https://image.tmdb.org/t/p/w1280/8cdWjvZQUExUUTzyp4t6EDMubfO.jpg",
                        releaseDate = "2024-08-27",
                        title = "Rebel Ridge",
                        video = false,
                        voteAverage = 6.797,
                        voteCount = 317,
                    ), Movie(
                        adult = false,
                        backdropPath = "/tbgIhYwQ5IAgNaFU1SBBxxNXCmm.jpg",
                        genreIds = arrayListOf(80, 28, 30),
                        id = 646097,
                        originalLanguage = "en",
                        originalTitle = "Rebel Ridge",
                        overview = "A former Marine confronts corruption in a small town when local law enforcement unjustly seizes the bag of cash he needs to post his cousin's bail.",
                        popularity = 831.205,
                        posterPath = "https://image.tmdb.org/t/p/w1280/8cdWjvZQUExUUTzyp4t6EDMubfO.jpg",
                        releaseDate = "2024-08-27",
                        title = "Rebel Ridge",
                        video = false,
                        voteAverage = 6.797,
                        voteCount = 317,
                    )
                )
            )
        })
    }
}

@Composable
fun HomeView(popularMovies: State<List<Movie>>) {

    val windowInfo = rememberWindowInfo()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = windowInfo.windowDimensions.verticalPadding * 2,
                end = windowInfo.windowDimensions.verticalPadding * 2
            )
    ) {

        if (popularMovies.value.isNotEmpty()) {
            Text(
                text = stringResource(id = R.string.popular_movies),
                color = Black,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.padding(windowInfo.windowDimensions.verticalPadding))

            LazyRow {
                items(popularMovies.value.size) { index ->
                    val movie = popularMovies.value[index]
                    PopularMoviesItemView(movie = movie, onItemClick = { })
                    Spacer(modifier = Modifier.padding(windowInfo.windowDimensions.verticalPadding))
                }
            }

            Spacer(modifier = Modifier.padding(windowInfo.windowDimensions.verticalPadding))
        }

        Text(
            text = stringResource(id = R.string.year_movies),
            color = Black,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.padding(windowInfo.windowDimensions.verticalPadding))
    }
}