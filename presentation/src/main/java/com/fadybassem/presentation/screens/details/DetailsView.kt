package com.fadybassem.presentation.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.fadybassem.core.R
import com.fadybassem.domain.model.Credits
import com.fadybassem.domain.model.Movie
import com.fadybassem.domain.model.Movies
import com.fadybassem.presentation.components.screen_size.rememberWindowInfo
import com.fadybassem.presentation.dummy.DummyMovie
import com.fadybassem.presentation.theme.AppTheme

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DetailsScreenPreview() {
    AppTheme {
        DetailsView(movieDetails = remember { mutableStateOf(DummyMovie.movie) },
            movieDetailsCredit = remember { mutableStateOf(DummyMovie.credit) },
            movieDetailsSimilar = remember { mutableStateOf(DummyMovie.movies) },
            onMovieClick = {})
    }
}

@Composable
fun DetailsView(
    movieDetails: State<Movie?>,
    movieDetailsCredit: State<Credits?>,
    movieDetailsSimilar: State<Movies?>,
    onMovieClick: (Movie) -> Unit,
) {
    val windowInfo = rememberWindowInfo()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        movieDetails.value?.let { movie ->
            item {
                Column(modifier = Modifier) {

                    // poster image
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current).data(movie.posterPath)
                            .error(R.drawable.image_placeholder)
                            .fallback(R.drawable.image_placeholder).build(),
                        contentDescription = stringResource(
                            id = R.string.content_description, "Profile picture"
                        ),
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(windowInfo.screenHeight / 2)
                            .background(
                                Color.LightGray
                            )
                    )

                    Spacer(modifier = Modifier.padding(windowInfo.windowDimensions.verticalPadding))

                    Column(
                        modifier = Modifier.padding(
                            start = windowInfo.windowDimensions.verticalPadding * 2,
                            end = windowInfo.windowDimensions.verticalPadding * 2
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally)
                        ) {
                            // rated r image
                            if (movie.adult == true) {
                                Image(
                                    painterResource(id = R.drawable.ic_rated_r),
                                    contentDescription = stringResource(
                                        id = R.string.content_description, "Rated R movie"
                                    ),
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .size(windowInfo.windowDimensions.verticalPadding * 2)
                                )

                                Spacer(modifier = Modifier.padding(windowInfo.windowDimensions.verticalPadding / 2))
                            }

                            // movie title
                            movie.originalTitle?.let {
                                Text(
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .weight(2f),
                                    text = it,
                                    color = Black,
                                    style = MaterialTheme.typography.titleLarge,
                                    textAlign = TextAlign.Start,
                                    fontWeight = FontWeight.SemiBold,
                                )
                            }

                            IconButton(
                                modifier = Modifier.align(Alignment.CenterVertically),
                                onClick = {}) {
                                Icon(imageVector = Icons.Filled.Bookmark, contentDescription = null)
                            }
                        }

                        // movie release date
                        movie.releaseDate?.let {
                            Text(
                                modifier = Modifier,
                                text = it,
                                color = Black,
                                style = MaterialTheme.typography.titleSmall,
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Thin,
                            )
                        }

                        Spacer(modifier = Modifier.padding(windowInfo.windowDimensions.verticalPadding))

                        // genres
                        movie.genreNames?.let {
                            Text(
                                modifier = Modifier,
                                text = "${stringResource(id = R.string.movie_genres)}: \n$it",
                                color = Black,
                                style = MaterialTheme.typography.titleSmall,
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Normal,
                            )
                        }

                        Spacer(modifier = Modifier.padding(windowInfo.windowDimensions.verticalPadding))

                        // movie overview value
                        movie.overview?.let {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterHorizontally),
                                text = "${stringResource(id = R.string.movie_overview)}: \n$it",
                                color = Black,
                                style = MaterialTheme.typography.titleMedium,
                                textAlign = TextAlign.Start,
                            )
                        }
                    }
                }
            }
        }

        movieDetailsCredit.value?.cast?.let { castList ->
            if(castList.isNotEmpty()) {
                item {
                    Column(
                        modifier = Modifier.padding(
                            start = windowInfo.windowDimensions.verticalPadding * 2,
                            end = windowInfo.windowDimensions.verticalPadding * 2
                        )
                    ) {
                        Spacer(modifier = Modifier.padding(windowInfo.windowDimensions.verticalPadding))

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(id = R.string.movie_cast),
                            color = Black,
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.SemiBold,
                        )

                        Spacer(modifier = Modifier.padding(windowInfo.windowDimensions.verticalPadding / 2))

                        LazyRow {
                            items(castList.size) { index ->
                                val cast = castList[index]
                                CastItemView(cast = cast)
                                Spacer(modifier = Modifier.padding(windowInfo.windowDimensions.verticalPadding))
                            }
                        }
                    }
                }
            }
        }

        movieDetailsSimilar.value?.results?.let { movies ->
            if (movies.isNotEmpty()) {
                item {
                    Column(
                        modifier = Modifier.padding(
                            start = windowInfo.windowDimensions.verticalPadding * 2,
                            end = windowInfo.windowDimensions.verticalPadding * 2
                        )
                    ) {
                        Spacer(modifier = Modifier.padding(windowInfo.windowDimensions.verticalPadding))

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(id = R.string.movie_similar_movies),
                            color = Black,
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.SemiBold,
                        )

                        Spacer(modifier = Modifier.padding(windowInfo.windowDimensions.verticalPadding / 2))

                        LazyRow {
                            items(movies.size) { index ->
                                val movie = movies[index]
                                SimilarMovieItemView(movie = movie, onItemClick = {
                                    onMovieClick.invoke(it)
                                })
                                Spacer(modifier = Modifier.padding(windowInfo.windowDimensions.verticalPadding))
                            }
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.padding(windowInfo.windowDimensions.verticalPadding))
        }
    }
}