package com.fadybassem.data.repository

import com.fadybassem.data.local.entities.MovieDao
import com.fadybassem.data.local.mapper.toMovieDomain
import com.fadybassem.domain.model.Movie
import com.fadybassem.domain.repository.WatchlistRepository
import javax.inject.Inject

class WatchlistRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao,
): WatchlistRepository {

    override suspend fun addMovieToWatchlist(movieId: Int) {
        movieDao.addToWatchlist(movieId)
    }

    override suspend fun removeMovieFromWatchlist(movieId: Int) {
        movieDao.removeFromWatchlist(movieId)
    }

    override suspend fun getWatchlistMovies(): List<Movie> {
        return movieDao.getWatchlistMovies().map { it.toMovieDomain() }
    }
}