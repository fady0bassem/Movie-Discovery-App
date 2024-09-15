package com.fadybassem.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fadybassem.core.HandleApiError
import com.fadybassem.data.local.entities.Converters
import com.fadybassem.data.local.entities.MovieDao
import com.fadybassem.data.local.mapper.toCreditsDomain
import com.fadybassem.data.local.mapper.toCreditsEntity
import com.fadybassem.data.local.mapper.toMovieDomain
import com.fadybassem.data.local.mapper.toMovieEntity
import com.fadybassem.data.local.mapper.toMoviesListDomain
import com.fadybassem.data.remote.api.ApiService
import com.fadybassem.data.remote.mapper.toCreditsDomain
import com.fadybassem.data.remote.mapper.toMovieDomain
import com.fadybassem.data.remote.mapper.toMoviesDomain
import com.fadybassem.data.remote.paging.MoviePagingSource
import com.fadybassem.domain.model.Credits
import com.fadybassem.domain.model.Movie
import com.fadybassem.domain.model.Movies
import com.fadybassem.domain.repository.MovieRepository
import com.fadybassem.util.NetworkManager
import com.fadybassem.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: ApiService,
    private val movieDao: MovieDao,
    private val handleApiError: HandleApiError,
    private val networkManager: NetworkManager,
) : MovieRepository {

    override fun getPopularMovies(sortBy: String): Flow<Resource<Movies>> = flow {
        try {
            emit(Resource.Loading())

            var cachedMovies = movieDao.getPopularMovies().first()

            if (networkManager.isNetworkConnected()) {
                val response = movieApi.getPopularMovies(sortBy = sortBy)
                val moviesDomain = response.toMoviesDomain()

                moviesDomain.results.forEach { movie ->
                    val cachedMovie = cachedMovies.find { it.id == movie.id }
                    if (cachedMovie != null) {
                        movieDao.updateMovieFields(
                            id = movie.id,
                            adult = movie.adult,
                            genreNames = movie.genreNames,
                            originalTitle = movie.originalTitle,
                            overview = movie.overview,
                            popularity = movie.popularity,
                            posterPath = movie.posterPath,
                            releaseDate = movie.releaseDate,
                            similarMovies = Converters().fromIntList(movie.similarMovies),
                            isInWatchlist = movie.isInWatchlist,
                            page = cachedMovie.page
                        )
                    } else {
                        movieDao.insertMovie(movie.toMovieEntity(isPopular = true))
                    }
                }

                cachedMovies = movieDao.getPopularMovies().first()

                if (cachedMovies.isNotEmpty()) {
                    emit(Resource.Success(data = Movies(results = cachedMovies.toMoviesListDomain())))
                } else {
                    emit(Resource.Success(data = moviesDomain))
                }
            } else {
                if (cachedMovies.isNotEmpty()) {
                    emit(Resource.Success(data = Movies(results = cachedMovies.toMoviesListDomain())))
                } else {
                    emit(Resource.Failed("No data available"))
                }
            }
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
        scope: CoroutineScope,
    ): Flow<Resource<PagingData<Movie>>> = flow {
        try {
            emit(Resource.Loading())

            val pagingDataFlow = Pager(config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                initialLoadSize = 20,
                prefetchDistance = 10
            ), pagingSourceFactory = {
                MoviePagingSource(
                    movieDao = movieDao,
                    movieApi = movieApi,
                    networkManager = networkManager,
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

    override fun getMovieDetails(id: Int): Flow<Resource<Movie>> = flow {
        try {
            emit(Resource.Loading())

            var cachedMovie = movieDao.getMovieById(id)

            if (networkManager.isNetworkConnected()) {
                val response = movieApi.getMovieDetails(id = id)
                val movieDomain = response.toMovieDomain()

                if (cachedMovie != null) {
                    movieDao.updateMovieFields(
                        id = movieDomain.id,
                        adult = movieDomain.adult,
                        genreNames = movieDomain.genreNames,
                        originalTitle = movieDomain.originalTitle,
                        overview = movieDomain.overview,
                        popularity = movieDomain.popularity,
                        posterPath = movieDomain.posterPath,
                        releaseDate = movieDomain.releaseDate,
                        similarMovies = cachedMovie.similarMovies,
                        isInWatchlist = cachedMovie.isInWatchlist,
                        page = cachedMovie.page
                    )

                } else {
                    movieDao.insertMovie(movieDomain.toMovieEntity(page = cachedMovie?.page))
                }

                cachedMovie = movieDao.getMovieById(id)

                if (cachedMovie != null) {
                    emit(Resource.Success(data = cachedMovie.toMovieDomain()))
                } else {
                    emit(Resource.Success(data = movieDomain))
                }
            } else {
                if (cachedMovie != null) {
                    emit(Resource.Success(data = cachedMovie.toMovieDomain()))
                }
            }
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

    override fun getMovieDetailsCredits(id: Int): Flow<Resource<Credits>> = flow {
        try {
            emit(Resource.Loading())

            var cachedCredits = movieDao.getCreditsById(id)

            if (networkManager.isNetworkConnected()) {
                val response = movieApi.getMovieDetailsCredits(id = id)
                val moviesDomain = response.toCreditsDomain()

                val creditsEntity = moviesDomain.toCreditsEntity()
                movieDao.insertCredits(creditsEntity)

                cachedCredits = movieDao.getCreditsById(id)

                if (cachedCredits != null) {
                    emit(Resource.Success(data = cachedCredits.toCreditsDomain()))
                } else {
                    emit(Resource.Success(data = moviesDomain))
                }
            } else {
                if (cachedCredits != null) {
                    emit(Resource.Success(data = cachedCredits.toCreditsDomain()))
                }
            }
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

    override fun getMovieDetailsSimilar(id: Int): Flow<Resource<Movies>> = flow {
        try {
            emit(Resource.Loading())

            val cachedMovie = movieDao.getMovieById(id)
            var similarMovies: List<Movie> = emptyList()

            if (networkManager.isNetworkConnected()) {
                val response = movieApi.getMovieDetailsSimilar(id = id)
                val moviesDomain = response.toMoviesDomain()

                moviesDomain.results.forEach { movie ->
                    val newMovieId = movie.id ?: -1

                    val originalMovie = movieDao.getMovieById(id)
                    val existingMovie = movieDao.getMovieById(newMovieId)

                    if (existingMovie == null) {
                        movieDao.insertMovie(movie.toMovieEntity())
                    }

                    originalMovie?.let { origMovie ->
                        val originalSimilarMoviesList =
                            origMovie.similarMovies?.let { similarMovieIdsString ->
                                Converters().toIntList(similarMovieIdsString)?.toMutableList()
                                    ?: mutableListOf()
                            } ?: mutableListOf()

                        if (!originalSimilarMoviesList.contains(newMovieId)) {
                            originalSimilarMoviesList.add(newMovieId)
                        }

                        movieDao.updateSimilarMovies(
                            origMovie.id,
                            Converters().fromIntList(originalSimilarMoviesList as ArrayList<Int>)
                        )
                    }
                }

                similarMovies = cachedMovie?.similarMovies?.let { similarMovieIdsString ->
                    if (similarMovieIdsString.isNotBlank()) {
                        val similarMovieIds = Converters().toIntList(similarMovieIdsString)

                        similarMovieIds?.mapNotNull { movieId ->
                            movieDao.getMovieById(movieId)?.toMovieDomain()
                        } ?: emptyList()
                    } else {
                        emptyList()
                    }
                } ?: emptyList()

                emit(Resource.Success(data = Movies(results = if (similarMovies.isEmpty()) moviesDomain.results else similarMovies as ArrayList<Movie>)))
            } else {
                if (cachedMovie != null) {
                    similarMovies = cachedMovie.similarMovies?.let { similarMovieIdsString ->
                        if (similarMovieIdsString.isNotBlank()) {
                            val similarMovieIds = Converters().toIntList(similarMovieIdsString)

                            similarMovieIds?.mapNotNull { movieId ->
                                movieDao.getMovieById(movieId)?.toMovieDomain()
                            } ?: emptyList()
                        } else {
                            emptyList()
                        }
                    } ?: emptyList()

                    emit(Resource.Success(data = Movies(results = similarMovies as ArrayList<Movie>)))
                }
            }
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