package com.fadybassem.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fadybassem.core.HandleApiError
import com.fadybassem.data.api.ApiService
import com.fadybassem.data.remote.mapper.toMoviesDomain
import com.fadybassem.domain.model.Movie
import retrofit2.HttpException

class MoviePagingSource(
    private val movieApi: ApiService,
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
        return try {
            val page = params.key ?: 1
            val response = movieApi.get2024Movies(sortBy = sortBy, year = year, page = page)
            val data = response.toMoviesDomain().results

            LoadResult.Page(
                data = data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (data.isEmpty()) null else page + 1
            )
        } catch (exception: HttpException) {
            val handleApiError = handleApiError.handleApiErrors(error = exception)
            LoadResult.Error(Throwable(handleApiError))
        } catch (exception: Exception) {
            LoadResult.Error(Throwable(exception.localizedMessage))
        }
    }
}
