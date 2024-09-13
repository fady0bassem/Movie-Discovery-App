package com.fadybassem.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fadybassem.core.HandleApiError
import com.fadybassem.data.api.ApiService
import com.fadybassem.data.remote.mapper.toMoviesDomain
import com.fadybassem.data.remote.paging.MoviePagingSource
import com.fadybassem.domain.model.Movie
import com.fadybassem.domain.model.Movies
import com.fadybassem.domain.repository.MovieRepository
import com.fadybassem.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: ApiService,
    private val handleApiError: HandleApiError,
) : MovieRepository {

    override fun getPopularMovies(sortBy: String): Flow<Resource<Movies>> = flow {
        try {
            emit(Resource.Loading())

            val response = movieApi.getPopularMovies(sortBy = sortBy)
            val moviesDomain = response.toMoviesDomain()

            emit(Resource.Success(data = moviesDomain))

        } catch (exception: HttpException) {
            exception.printStackTrace()
            val handleApiError = handleApiError.handleApiErrors(error = exception)
            emit(
                Resource.Failed(
                    message = handleApiError, code = exception.code(), exception = exception
                )
            )
        } catch (exception: Exception) {
            exception.printStackTrace()
            emit(Resource.Error(message = exception.localizedMessage))
        }
    }

    override fun get2024MoviesPagingSource(
        sortBy: String,
        year: Int,
        scope: CoroutineScope
    ): Flow<Resource<PagingData<Movie>>> = flow {
        try {
            emit(Resource.Loading())

            val pagingDataFlow = Pager(config = PagingConfig(
                pageSize = 20, enablePlaceholders = false, initialLoadSize = 20
            ), pagingSourceFactory = {
                MoviePagingSource(
                    movieApi = movieApi,
                    handleApiError = handleApiError,
                    sortBy = sortBy,
                    year = year
                )
            }).flow.cachedIn(scope)

            emitAll(pagingDataFlow.map { pagingData ->
                Resource.Success(pagingData)
            })

        } catch (exception: HttpException) {
            exception.printStackTrace()
            val handleApiError = handleApiError.handleApiErrors(error = exception)
            emit(
                Resource.Failed(
                    message = handleApiError, code = exception.code(), exception = exception
                )
            )
        } catch (exception: Exception) {
            exception.printStackTrace()
            emit(Resource.Error(message = exception.localizedMessage))
        }
    }
}