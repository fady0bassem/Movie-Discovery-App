package com.fadybassem.domain.usecase.details

import com.fadybassem.domain.model.Movies
import com.fadybassem.domain.repository.MovieRepository
import com.fadybassem.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieDetailsSimilarUseCase @Inject constructor(private val repository: MovieRepository) {
    fun execute(id: Int): Flow<Resource<Movies>> = flow {
        repository.getMovieDetailsSimilar(id = id).collect { resource ->
            emit(resource)
        }
    }
}