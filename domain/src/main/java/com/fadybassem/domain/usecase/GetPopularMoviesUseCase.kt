package com.fadybassem.domain.usecase

import com.fadybassem.domain.model.Movies
import com.fadybassem.domain.repository.MovieRepository
import com.fadybassem.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    fun execute(sortBy: String): Flow<Resource<Movies>> {
        return movieRepository.getPopularMovies(sortBy = sortBy)
    }
}
