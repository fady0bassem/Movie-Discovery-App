package com.fadybassem.domain.usecase

import androidx.paging.PagingData
import com.fadybassem.core.R
import com.fadybassem.core.ResourceProvider
import com.fadybassem.domain.model.Movie
import com.fadybassem.domain.repository.MovieRepository
import com.fadybassem.util.NetworkManager
import com.fadybassem.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Get2024MoviesPagingSourceUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val networkManager: NetworkManager,
    private val resourceProvider: ResourceProvider,
) {

    fun execute(sortBy: String, year: Int,scope: CoroutineScope): Flow<Resource<PagingData<Movie>>> = flow {
        if (!networkManager.isNetworkConnected()) {
            emit(Resource.Error(message = resourceProvider.getString(R.string.no_internet_connection)))
            return@flow
        }

        repository.get2024MoviesPagingSource(sortBy = sortBy, year = year, scope = scope).collect { resource ->
            emit(resource)
        }
    }
}