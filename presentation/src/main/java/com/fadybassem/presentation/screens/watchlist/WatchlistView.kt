package com.fadybassem.presentation.screens.watchlist

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.fadybassem.presentation.components.screen_size.rememberWindowInfo
import com.fadybassem.presentation.theme.AppTheme

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun WatchlistScreenPreview() {
    AppTheme {
        WatchlistView()
    }
}

@Composable
fun WatchlistView() {

    val windowInfo = rememberWindowInfo()

    Text(text = "Watchlist View")
}