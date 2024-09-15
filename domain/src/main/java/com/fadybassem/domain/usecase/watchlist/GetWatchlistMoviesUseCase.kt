package com.fadybassem.domain.usecase.watchlist

import com.fadybassem.domain.model.Movie
import com.fadybassem.domain.repository.WatchlistRepository
import javax.inject.Inject

class GetWatchlistMoviesUseCase @Inject constructor(
    private val repository: WatchlistRepository
) {
    suspend fun execute(): List<Movie> {
        return repository.getWatchlistMovies()
    }
}
