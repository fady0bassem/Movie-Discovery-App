package com.fadybassem.presentation.screens.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.fadybassem.core.R
import com.fadybassem.domain.model.Movie
import com.fadybassem.presentation.components.screen_size.rememberWindowInfo
import com.fadybassem.presentation.dummy.DummyMovie
import com.fadybassem.presentation.theme.AppTheme

@Preview(showBackground = true, showSystemUi = true, apiLevel = 33)
@Composable
private fun SimilarMovieItemPreview() {
    AppTheme {
        SimilarMovieItemView(movie = DummyMovie.movie, onItemClick = {})
    }
}

@Composable
internal fun SimilarMovieItemView(movie: Movie, onItemClick: (Movie) -> Unit) {
    val windowInfo = rememberWindowInfo()

    Card(
        modifier = Modifier
            .size(windowInfo.windowDimensions.verticalPadding * 24)
            .wrapContentSize()
            .clickable { onItemClick.invoke(movie) },
        elevation = CardDefaults.cardElevation(0.dp),
        shape = RoundedCornerShape(windowInfo.windowDimensions.verticalPadding * 2),
        colors = CardDefaults.cardColors(containerColor = White),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Box {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(movie.posterPath)
                    .error(R.drawable.image_placeholder).fallback(R.drawable.image_placeholder)
                    .build(), contentDescription = stringResource(
                    id = R.string.content_description, "Profile picture"
                ), contentScale = ContentScale.Crop, modifier = Modifier.background(Color.LightGray)
            )

            movie.originalTitle?.let {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .background(Color.Gray.copy(alpha = 0.8f))
                        .padding(windowInfo.windowDimensions.verticalPadding)
                ) {

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterStart),
                        text = it,
                        color = White,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}