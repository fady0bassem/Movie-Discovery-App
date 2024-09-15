package com.fadybassem.domain.usecase.watchlist

import com.fadybassem.domain.repository.WatchlistRepository
import javax.inject.Inject

class RemoveMovieFromWatchlistUseCase @Inject constructor(
    private val repository: WatchlistRepository,
) {
    suspend fun execute(movieId: Int) {
        repository.removeMovieFromWatchlist(movieId = movieId)
    }
}
