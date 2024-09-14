package com.fadybassem.presentation.screens.details

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavHostController
import com.fadybassem.presentation.theme.AppTheme
import com.fadybassem.util.ObserveLifecycleEvents

@Composable
fun DetailsScreen(navController: NavHostController, viewModel: DetailsViewModel = hiltViewModel()) {
    viewModel.ObserveLifecycleEvents(LocalLifecycleOwner.current.lifecycle)

    val context = LocalContext.current
    val activity = context as Activity

    val apiStatus by remember { viewModel.apiStatus }

    AppTheme(apiStatus = apiStatus) {
        Box(modifier = Modifier.fillMaxSize()) {
            DetailsView()
        }
    }
}