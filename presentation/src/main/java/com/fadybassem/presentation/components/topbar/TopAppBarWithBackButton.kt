package com.fadybassem.presentation.components.topbar

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
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithBackButton(
    navController: NavHostController,
    title: String,
    showNavigationIcon: Boolean,
) {
    CenterAlignedTopAppBar(title = {
        Text(
            modifier = Modifier,
            text = title,
            color = Black,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
        )
    }, navigationIcon = {
        if (showNavigationIcon) {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
        } else null
    }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White))
}