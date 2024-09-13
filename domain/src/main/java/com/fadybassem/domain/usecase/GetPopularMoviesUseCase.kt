package com.fadybassem.domain.usecase

import com.fadybassem.core.R
import com.fadybassem.core.ResourceProvider
import com.fadybassem.domain.model.Movie
import com.fadybassem.domain.model.Movies
import com.fadybassem.domain.repository.MovieRepository
import com.fadybassem.util.NetworkManager
import com.fadybassem.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val networkManager: NetworkManager,
    private val resourceProvider: ResourceProvider
) {
    fun execute(sortBy: String): Flow<Resource<Movies>> = flow {
        // Check network availability before making the API call
        if (!networkManager.isNetworkConnected()) {
            emit(Resource.Error(message = resourceProvider.getString(R.string.no_internet_connection)))
            return@flow
        }

        // Proceed to fetch the data if network is available
        movieRepository.getPopularMovies(sortBy = sortBy).collect { resource ->
            when (resource) {
                is Resource.Success -> {
                    val data = resource.data
                    val limitedMovies = resource.data?.results?.take(10) // Get only first 10 movies
                    data?.results = limitedMovies as ArrayList<Movie>
                    emit(Resource.Success(data = data)) // Emit the limited movie list
                }
                else -> {
                    emit(resource) // Pass through the Loading, Error, or other states
                }
            }
        }
    }
}
