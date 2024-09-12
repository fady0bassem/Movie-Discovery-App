package com.fadybassem.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.fadybassem.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithBackButton(navController: NavHostController) {
    CenterAlignedTopAppBar(title = {
        Text(
            modifier = Modifier,
            text = when (currentRoute(navController)) {
                MainRoutes.Home.route -> stringResource(id = R.string.home)
                MainRoutes.Watchlist.route -> stringResource(id = R.string.watchlist)
                else -> stringResource(id = R.string.app_name)
            },
            color = Black,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
        )
    }, navigationIcon = {
        if (currentRoute(navController) != MainRoutes.Home.route) {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
        } else null
    }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White))
}