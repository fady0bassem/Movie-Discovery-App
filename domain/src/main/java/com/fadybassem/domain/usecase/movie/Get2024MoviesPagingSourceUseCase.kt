package com.fadybassem.domain.usecase.movie

import androidx.paging.PagingData
import com.fadybassem.domain.model.Movie
import com.fadybassem.domain.repository.MovieRepository
import com.fadybassem.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Get2024MoviesPagingSourceUseCase @Inject constructor(private val repository: MovieRepository) {

    fun execute(
        sortBy: String,
        year: Int,
        scope: CoroutineScope,
    ): Flow<Resource<PagingData<Movie>>> = flow {
        repository.get2024MoviesPagingSource(sortBy = sortBy, year = year, scope = scope)
            .collect { resource ->
                emit(resource)
            }
    }
}