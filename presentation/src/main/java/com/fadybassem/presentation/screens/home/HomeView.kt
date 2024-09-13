package com.fadybassem.presentation.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.fadybassem.core.R
import com.fadybassem.domain.model.Movie
import com.fadybassem.presentation.components.screen_size.rememberWindowInfo
import com.fadybassem.presentation.theme.AppTheme

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    AppTheme {
        val dummyPagingData: LazyPagingItems<Pair<Movie, String>> =
            Pager(PagingConfig(pageSize = 30)) {
                object : PagingSource<Int, Pair<Movie, String>>() {
                    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pair<Movie, String>> {
                        return LoadResult.Page(
                            data = List(2) { index ->
                                Pair(
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
                                    ), "April"
                                )
                            }, prevKey = null, nextKey = 1 // Adjust based on your paging logic
                        )
                    }

                    override fun getRefreshKey(state: PagingState<Int, Pair<Movie, String>>): Int {
                        return 0
                    }
                }
            }.flow.collectAsLazyPagingItems()

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
        }, moviesPageFlow = dummyPagingData)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeView(
    popularMovies: State<List<Movie>>,
    moviesPageFlow: LazyPagingItems<Pair<Movie, String>>,
) {

    val windowInfo = rememberWindowInfo()

    val listState = rememberLazyListState()

    var lastVisibleMonth by remember { mutableStateOf<String?>(null) } // Track the last visible month

    var stickyHeaderMonth by remember { mutableStateOf<String?>(null) }

    val headerBackgroundColor = remember { mutableStateOf(Color.White) }

    val isHeaderSticky by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0 ||
                    (listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset > 0)
        }
    }

    LaunchedEffect(isHeaderSticky) {
        headerBackgroundColor.value = if (isHeaderSticky) Color.Gray.copy(alpha = 0.8f) else Color.White
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            ,
        state = listState
    ) {
        item {
            Column(modifier = Modifier.padding(
                start = windowInfo.windowDimensions.verticalPadding * 2,
                end = windowInfo.windowDimensions.verticalPadding * 2
            )) {
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
                            PopularMovieItemView(movie = movie, onItemClick = { })
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

        stickyHeader {
            stickyHeaderMonth?.let { month ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(headerBackgroundColor.value)
                    
                ) {
                    Spacer(modifier = Modifier.padding(windowInfo.windowDimensions.verticalPadding * 2))

                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = month,
                        color = Black,
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                    )

                    Spacer(modifier = Modifier.padding(windowInfo.windowDimensions.verticalPadding * 2))

                }
            }
        }

        items(moviesPageFlow.itemCount) { index ->
            val movieWithMonth = moviesPageFlow[index]

            movieWithMonth?.let { (movie, month) ->

                /*val previousMovieWithMonth = if (index > 0) moviesPageFlow[index - 1] else null
                val previousMonth = previousMovieWithMonth?.second

                if (previousMonth != month) {
                    Text(
                        text = month,
                        color = Black,
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.padding(windowInfo.windowDimensions.verticalPadding))
                }*/

                val isNewMonth = index == 0 || (index > 0 && month != (moviesPageFlow[index - 1]?.second ?: ""))

                if (isNewMonth) {
                    lastVisibleMonth = month
                }

                if(lastVisibleMonth != month){
                    lastVisibleMonth = month
                }

                if (index == listState.firstVisibleItemIndex) {
                    stickyHeaderMonth = lastVisibleMonth
                }

                Spacer(modifier = Modifier.padding(windowInfo.windowDimensions.verticalPadding))

                MovieItemView(movie = movie, onItemClick = { })
            }
        }

        item {
            Spacer(modifier = Modifier.padding(windowInfo.windowDimensions.verticalPadding))
        }

    }
}