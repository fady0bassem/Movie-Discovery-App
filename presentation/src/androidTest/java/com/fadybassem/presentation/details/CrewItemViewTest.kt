package com.fadybassem.presentation.details

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fadybassem.presentation.dummy.DummyMovie
import com.fadybassem.presentation.screens.details.CrewItemView
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CrewItemViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testCrewProfilePathValid() {
        val crew = DummyMovie.credit.crew[0]

        composeTestRule.setContent {
            CrewItemView(crew)
        }

        // check that the image with the profilePath is displayed
        composeTestRule.onNodeWithContentDescription("crew image").assertIsDisplayed()
    }

    @Test
    fun testCrewName() {
        val crew = DummyMovie.credit.crew[0]

        composeTestRule.setContent {
            CrewItemView(crew)
        }

        composeTestRule.waitForIdle()

        // check to find the text component
        crew.name?.let {
            composeTestRule.onNodeWithText(it).assertIsDisplayed()
        }
    }

    @Test
    fun testCrewKnownForDepartment() {
        val crew = DummyMovie.credit.crew[0]

        composeTestRule.setContent {
            CrewItemView(crew)
        }

        composeTestRule.waitForIdle()

        // check to find the text component
        crew.knownForDepartment?.let {
            composeTestRule.onNodeWithText(it).assertIsDisplayed()
        }
    }
}