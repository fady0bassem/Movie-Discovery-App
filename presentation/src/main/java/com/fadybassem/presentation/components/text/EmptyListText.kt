package com.fadybassem.presentation.components.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.fadybassem.core.R
import com.fadybassem.presentation.theme.AppTheme

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun EmptyListPreview() {
    AppTheme {
        EmptyListText()
    }
}

@Composable
fun EmptyListText(
    text: String = stringResource(id = R.string.no_data_available),
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // empty data text
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = text,
            color = Color.LightGray,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Normal
        )
    }
}