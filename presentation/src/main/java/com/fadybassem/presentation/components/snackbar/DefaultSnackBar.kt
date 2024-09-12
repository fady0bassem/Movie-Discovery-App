package com.fadybassem.presentation.components.snackbar

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SnackBarController(private val scope: CoroutineScope) {

    private var snackBarJob: Job? = null

    fun getScope() = scope

    init {
        cancelActiveJob()
    }

    fun showSnackBar(scaffoldState: ScaffoldState?, message: String, actionLabel: String) {
        if (scaffoldState == null)
            return

        if (snackBarJob == null) {
            snackBarJob = scope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = message, actionLabel = actionLabel, duration = SnackbarDuration.Short
                )
                cancelActiveJob()
            }
        } else {
            cancelActiveJob()
            snackBarJob = scope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = message, actionLabel = actionLabel, duration = SnackbarDuration.Short
                )
                cancelActiveJob()
            }
        }
    }

    private fun cancelActiveJob() {
        snackBarJob?.let {
            it.cancel()
            snackBarJob = Job()
        }
    }
}