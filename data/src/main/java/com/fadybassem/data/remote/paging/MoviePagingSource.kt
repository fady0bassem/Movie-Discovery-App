package com.fadybassem.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fadybassem.core.HandleApiError
import com.fadybassem.data.local.entities.Converters
import com.fadybassem.data.local.entities.MovieDao
import com.fadybassem.data.local.entities.MovieEntity
import com.fadybassem.data.remote.mapper.toMoviesDomain
import com.fadybassem.data.remote.api.ApiService
import com.fadybassem.data.local.mapper.toMovieDomain
import com.fadybassem.data.local.mapper.toMovieEntity
import com.fadybassem.domain.model.Movie
import com.fadybassem.util.NetworkManager
import retrofit2.HttpException

class MoviePagingSource(
    private val movieApi: ApiService,
    private val movieDao: MovieDao,
    private val networkManager: NetworkManager,
    private val handleApiError: HandleApiError,
    private val sortBy: String,
    private val year: Int,
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        try {
            val page = params.key ?: 1

            val cachedMovies = movieDao.getMoviesByPage(page)

            if (cachedMovies.isNotEmpty()) {
                val cachedResult = LoadResult.Page(
                    data = cachedMovies.reversed().map { it.toMovieDomain() },
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (cachedMovies.isEmpty()) null else page + 1
                )

                if (networkManager.isNetworkConnected()) {
                    updateCacheWithNetworkData(page, cachedMovies)
                }

                return cachedResult
            }

            return if (networkManager.isNetworkConnected()) {
                fetchMoviesFromNetwork(page, cachedMovies)
            } else {
                LoadResult.Error(Throwable("No Internet Connection"))
            }

        } catch (exception: HttpException) {
            val handleApiError = handleApiError.handleApiErrors(error = exception)
            return LoadResult.Error(Throwable(handleApiError))
        } catch (exception: Exception) {
            return LoadResult.Error(Throwable(exception.localizedMessage))
        }
    }

    private suspend fun fetchMoviesFromNetwork(page: Int, cachedMovies: List<MovieEntity>): LoadResult<Int, Movie> {
        val response = movieApi.get2024Movies(sortBy = sortBy, year = year, page = page)
        val data = response.toMoviesDomain().results

        data.forEach { movie->
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
                    page = page
                )
            } else {
                movieDao.insertMovie(movie.toMovieEntity(page = page))
            }
        }

        return LoadResult.Page(
            data = data,
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (data.isEmpty()) null else page + 1
        )
    }

    private suspend fun updateCacheWithNetworkData(page: Int, cachedMovies: List<MovieEntity>) {
        try {
            val response = movieApi.get2024Movies(sortBy = sortBy, year = year, page = page)
            val data = response.toMoviesDomain().results

            data.forEach { movie->
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
                        page = page
                    )
                } else {
                    movieDao.insertMovie(movie.toMovieEntity(page = page))
                }
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }
}
