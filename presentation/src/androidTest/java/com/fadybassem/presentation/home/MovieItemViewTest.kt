package com.fadybassem.presentation.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fadybassem.domain.model.Movie
import com.fadybassem.presentation.dummy.DummyMovie
import com.fadybassem.presentation.screens.home.MovieItemView
import com.fadybassem.presentation.screens.home.PopularMovieItemView
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieItemViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testMoviePosterPathValid() {
        val movie = DummyMovie.movie

        composeTestRule.setContent {
            MovieItemView(movie = movie, onItemClick = {})
        }

        // check that the image with the posterPath is displayed
        composeTestRule.onNodeWithContentDescription("poster image").assertIsDisplayed()
    }

    @Test
    fun testMovieOriginalTitle() {
        val movie = DummyMovie.movie

        composeTestRule.setContent {
            MovieItemView(movie = movie, onItemClick = {})
        }

        composeTestRule.waitForIdle()

        // check to find the text component
        movie.originalTitle?.let {
            composeTestRule.onNodeWithText(it).assertIsDisplayed()
        }
    }

    @Test
    fun testMovieReleaseDate() {
        val movie = DummyMovie.movie

        composeTestRule.setContent {
            MovieItemView(movie = movie, onItemClick = {})
        }

        composeTestRule.waitForIdle()

        // check to find the text component
        movie.releaseDate?.let {
            composeTestRule.onNodeWithText(it).assertIsDisplayed()
        }
    }

    @Test
    fun testMovieOverview() {
        val movie = DummyMovie.movie

        composeTestRule.setContent {
            MovieItemView(movie = movie, onItemClick = {})
        }

        composeTestRule.waitForIdle()

        // check to find the text component
        movie.overview?.let {
            composeTestRule.onNodeWithText(it).assertIsDisplayed()
        }
    }

    @Test
    fun testMovieCardClick() {
        val movie = DummyMovie.movie
        var clickedMovie: Movie? = null

        composeTestRule.setContent {
            PopularMovieItemView(movie = movie, onItemClick = { clickedMovie = it })
        }

        // perform click on the card
        composeTestRule.onNodeWithTag("card").performClick()

        // verify that the movie clicked is the one passed to onItemClick
        assert(clickedMovie == movie)
    }
}