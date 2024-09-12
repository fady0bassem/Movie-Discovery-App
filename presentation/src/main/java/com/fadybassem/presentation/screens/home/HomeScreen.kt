package com.fadybassem.presentation.screens.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.fadybassem.presentation.theme.AppTheme

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    AppTheme {
        HomeScreen()
    }
}

@Composable
fun HomeScreen(
    //viewModel: HomeViewModel = hiltViewModel(),
) {
    Text("Home")
}