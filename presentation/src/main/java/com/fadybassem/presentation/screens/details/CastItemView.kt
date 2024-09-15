package com.fadybassem.presentation.screens.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.fadybassem.core.R
import com.fadybassem.domain.model.Cast
import com.fadybassem.presentation.components.screen_size.rememberWindowInfo
import com.fadybassem.presentation.dummy.DummyMovie
import com.fadybassem.presentation.theme.AppTheme

@Preview(showBackground = true, showSystemUi = true, apiLevel = 33)
@Composable
private fun CastItemPreview() {
    AppTheme {
        DummyMovie.credit.cast.get(0)?.let { CastItemView(cast = it) }
    }
}


@Composable
fun CastItemView(cast: Cast) {

    val windowInfo = rememberWindowInfo()

    Card(
        modifier = Modifier
            .width(windowInfo.windowDimensions.verticalPadding * 24)
            .height(windowInfo.windowDimensions.verticalPadding * 32),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = RoundedCornerShape(windowInfo.windowDimensions.verticalPadding * 2),
        colors = CardDefaults.cardColors(containerColor = White),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Column(modifier = Modifier.wrapContentSize()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(cast.profilePath)
                    .error(R.drawable.image_placeholder).fallback(R.drawable.image_placeholder)
                    .diskCacheKey(cast.profilePath).memoryCacheKey(cast.profilePath)
                    .diskCachePolicy(CachePolicy.ENABLED).memoryCachePolicy(CachePolicy.ENABLED)
                    .build(),
                contentDescription = stringResource(
                    id = R.string.content_description, "Profile picture"
                ),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(windowInfo.windowDimensions.verticalPadding * 24)
                    .background(Color.LightGray)
            )

            Spacer(modifier = Modifier.padding(windowInfo.windowDimensions.verticalPadding / 2))

            cast.name?.let {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = windowInfo.windowDimensions.verticalPadding * 2,
                            end = windowInfo.windowDimensions.verticalPadding * 2
                        )
                        .align(Alignment.CenterHorizontally),
                    text = it,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                )

                cast.character?.let { it1 ->
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = windowInfo.windowDimensions.verticalPadding * 2,
                                end = windowInfo.windowDimensions.verticalPadding * 2
                            )
                            .align(Alignment.CenterHorizontally),
                        text = it1,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Normal,
                    )
                }
            }
        }
    }
}