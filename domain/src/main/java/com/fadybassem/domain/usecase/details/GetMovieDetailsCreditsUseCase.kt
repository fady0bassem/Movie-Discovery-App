package com.fadybassem.domain.usecase.details

import com.fadybassem.domain.model.Credits
import com.fadybassem.domain.repository.MovieRepository
import com.fadybassem.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieDetailsCreditsUseCase @Inject constructor(private val repository: MovieRepository) {
    fun execute(id: Int): Flow<Resource<Credits>> = flow {
        repository.getMovieDetailsCredits(id = id).collect { resource ->
            emit(resource)
        }
    }
}