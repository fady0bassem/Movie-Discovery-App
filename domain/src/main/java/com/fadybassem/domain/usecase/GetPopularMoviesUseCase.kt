package com.fadybassem.domain.usecase

import com.fadybassem.domain.model.Movie
import com.fadybassem.domain.model.Movies
import com.fadybassem.domain.repository.MovieRepository
import com.fadybassem.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    fun execute(sortBy: String): Flow<Resource<Movies>> = flow {
        movieRepository.getPopularMovies(sortBy = sortBy).collect { resource ->
            when (resource) {
                is Resource.Success -> {
                    val data = resource.data
                    val limitedMovies = resource.data?.results?.take(10) // Get only first 10 movies
                    data?.results = limitedMovies as ArrayList<Movie>
                    emit(Resource.Success(data = data))
                }

                else -> {
                    emit(resource)
                }
            }
        }
    }
}
