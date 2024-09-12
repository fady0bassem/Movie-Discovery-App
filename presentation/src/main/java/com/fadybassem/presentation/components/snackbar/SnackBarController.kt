package com.fadybassem.presentation.components.snackbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DefaultSnackBar(
    snackBarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit
) {
    SnackbarHost(
        modifier = modifier,
        hostState = snackBarHostState,
        snackbar = { data ->
            Snackbar(
                modifier = Modifier.padding(8.dp),
                action = {
                    data.actionLabel?.let { actionLabel ->
                        TextButton(onClick = { onDismiss() }) {
                            Text(
                                text = actionLabel,
                                style = MaterialTheme.typography.subtitle1,
                                color = MaterialTheme.colors.surface
                            )
                        }
                    }
                }
            ) {
                Text(
                    text = data.message,
                    style = MaterialTheme.typography.subtitle2,
                    color = MaterialTheme.colors.surface
                )
            }
        }
    )
}