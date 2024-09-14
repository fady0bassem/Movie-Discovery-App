package com.fadybassem.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.fadybassem.presentation.components.network_state.ConnectivityStatus
import com.fadybassem.presentation.components.progressbar.CircularIndeterminateProgressBar
import com.fadybassem.presentation.components.snackbar.DefaultSnackBar
import com.fadybassem.util.Status
import com.fadybassem.util.disableTouch
import com.fadybassem.util.enableTouch
import com.fadybassem.util.getActivity
import com.fadybassem.util.hideKeyboard

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    showNetworkError: Boolean = false,
    apiStatus: Status? = null,
    scaffoldState: ScaffoldState? = null,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    val colorScheme = LightColorScheme

    MaterialTheme(colorScheme = colorScheme, typography = Typography) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize()) {
                if(showNetworkError)
                ConnectivityStatus()

                content.invoke()
            }

            CircularIndeterminateProgressBar(isDisplayed = apiStatus == Status.LOADING)

            scaffoldState?.let { state ->
                DefaultSnackBar(modifier = Modifier.align(Alignment.BottomCenter),
                    snackBarHostState = state.snackbarHostState,
                    onDismiss = {
                        state.snackbarHostState.currentSnackbarData?.dismiss()
                    })
            }

            when (apiStatus) {
                Status.LOADING -> {
                    context.getActivity()?.disableTouch()
                    context.getActivity()?.hideKeyboard()
                }
                else -> {
                    context.getActivity()?.enableTouch()
                }
            }
        }
    }
}