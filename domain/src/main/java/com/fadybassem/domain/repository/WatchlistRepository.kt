package com.fadybassem.domain.repository

import com.fadybassem.domain.model.Movie

interface WatchlistRepository {
    suspend fun addMovieToWatchlist(movieId: Int)
    suspend fun removeMovieFromWatchlist(movieId: Int)
    suspend fun getWatchlistMovies(): List<Movie>
}