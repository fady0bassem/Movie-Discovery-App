package com.fadybassem.presentation.details

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fadybassem.presentation.dummy.DummyMovie
import com.fadybassem.presentation.screens.details.CastItemView
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CastItemViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testCastProfilePathValid() {
        val cast = DummyMovie.credit.cast[0]

        composeTestRule.setContent {
            CastItemView(cast)
        }

        // check that the image with the profilePath is displayed
        composeTestRule.onNodeWithContentDescription("cast image").assertIsDisplayed()
    }

    @Test
    fun testCastName() {
        val cast = DummyMovie.credit.cast[0]

        composeTestRule.setContent {
            CastItemView(cast)
        }

        composeTestRule.waitForIdle()

        // check to find the text component
        cast.name?.let {
            composeTestRule.onNodeWithText(it).assertIsDisplayed()
        }
    }

    @Test
    fun testCastCharacter() {
        val cast = DummyMovie.credit.cast[0]

        composeTestRule.setContent {
            CastItemView(cast)
        }

        composeTestRule.waitForIdle()

        // check to find the text component
        cast.character?.let {
            composeTestRule.onNodeWithText(it).assertIsDisplayed()
        }
    }
}