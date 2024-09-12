package com.fadybassem.presentation.components.progressbar

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.fadybassem.presentation.theme.AppTheme

private const val progressBar = "progressBar"
private const val text = "text"

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CircularIndeterminateProgressBarPreview() {
    AppTheme() {
        CircularIndeterminateProgressBar(isDisplayed = true)
    }
}

@Composable
fun CircularIndeterminateProgressBar(isDisplayed: Boolean) {
    if (isDisplayed) {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val constraints = if (minWidth < 600.dp) { // portrait
                myDecoupledConstraints(0.5f)
            } else {
                myDecoupledConstraints(0.5f)
            }

            ConstraintLayout(modifier = Modifier.fillMaxSize(), constraintSet = constraints) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary, modifier = Modifier.layoutId(progressBar)
                )
            }
        }
    }
}

private fun myDecoupledConstraints(verticalBias: Float): ConstraintSet {
    return ConstraintSet {
        val guideline = createGuidelineFromTop(verticalBias)
        val progressBar = createRefFor(progressBar)
        val text = createRefFor(text)

        constrain(progressBar) {
            top.linkTo(guideline)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(text) {
            top.linkTo(progressBar.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    }
}