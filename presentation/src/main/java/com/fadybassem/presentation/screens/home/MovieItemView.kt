package com.fadybassem.presentation.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.fadybassem.core.R
import com.fadybassem.domain.model.Movie
import com.fadybassem.presentation.components.screen_size.rememberWindowInfo
import com.fadybassem.presentation.dummy.DummyMovie
import com.fadybassem.presentation.theme.AppTheme

@Preview(showBackground = true, showSystemUi = true, apiLevel = 33)
@Composable
private fun PopularMovieItemPreview() {
    AppTheme {
        MovieItemView(movie = DummyMovie.movie, onItemClick = {})
    }
}


@Composable
internal fun MovieItemView(movie: Movie, onItemClick: (Movie) -> Unit) {

    val windowInfo = rememberWindowInfo()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = windowInfo.windowDimensions.verticalPadding * 2,
                end = windowInfo.windowDimensions.verticalPadding * 2
            )
            .size(windowInfo.windowDimensions.verticalPadding * 16)
            .clickable { onItemClick.invoke(movie) },
        elevation = CardDefaults.cardElevation(0.dp),
        shape = RoundedCornerShape(windowInfo.windowDimensions.verticalPadding * 2),
        colors = CardDefaults.cardColors(containerColor = White),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Row {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(movie.posterPath)
                    .error(R.drawable.image_placeholder).fallback(R.drawable.image_placeholder)
                    .diskCacheKey(movie.posterPath).memoryCacheKey(movie.posterPath)
                    .diskCachePolicy(CachePolicy.ENABLED).memoryCachePolicy(CachePolicy.ENABLED)
                    .build(),
                contentDescription = stringResource(
                    id = R.string.content_description, "Profile picture"
                ),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Transparent)
            )

            Column(
                modifier = Modifier
                    .weight(1.5f)
                    .padding(windowInfo.windowDimensions.verticalPadding)
            ) {
                movie.originalTitle?.let {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        text = it,
                        color = Black,
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.SemiBold,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2
                    )
                }

                movie.releaseDate?.let {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        text = it,
                        color = Black,
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Thin,
                    )
                }

                movie.overview?.let {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        text = it,
                        color = Black,
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.SemiBold,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 3
                    )
                }
            }
        }
    }
}