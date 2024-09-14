package com.fadybassem.domain.usecase

import com.fadybassem.core.R
import com.fadybassem.core.ResourceProvider
import com.fadybassem.domain.model.Credits
import com.fadybassem.domain.repository.MovieRepository
import com.fadybassem.util.NetworkManager
import com.fadybassem.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieDetailsCreditsUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val networkManager: NetworkManager,
    private val resourceProvider: ResourceProvider,
) {
    fun execute(id: Int): Flow<Resource<Credits>> = flow {
        // Check network availability before making the API call
        if (!networkManager.isNetworkConnected()) {
            emit(Resource.Error(message = resourceProvider.getString(R.string.no_internet_connection)))
            return@flow
        }

        // Proceed to fetch the data if network is available
        repository.getMovieDetailsCredits(id = id).collect { resource ->
            emit(resource)
        }
    }
}