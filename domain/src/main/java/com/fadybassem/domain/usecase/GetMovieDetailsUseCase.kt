package com.fadybassem.domain.usecase

import com.fadybassem.domain.model.Movie
import com.fadybassem.domain.repository.MovieRepository
import com.fadybassem.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val repository: MovieRepository) {
    fun execute(id: Int): Flow<Resource<Movie>> = flow {
        repository.getMovieDetails(id = id).collect { resource ->
            emit(resource)
        }
    }
}