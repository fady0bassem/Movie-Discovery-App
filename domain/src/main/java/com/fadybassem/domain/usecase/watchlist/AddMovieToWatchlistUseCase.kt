package com.fadybassem.domain.usecase.watchlist

import com.fadybassem.domain.repository.WatchlistRepository
import javax.inject.Inject

class AddMovieToWatchlistUseCase @Inject constructor(
    private val repository: WatchlistRepository,
) {
    suspend fun execute(movieId: Int) {
        repository.addMovieToWatchlist(movieId = movieId)
    }
}