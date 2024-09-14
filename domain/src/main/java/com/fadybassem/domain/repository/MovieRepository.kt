package com.fadybassem.domain.repository

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.fadybassem.domain.model.Credits
import com.fadybassem.domain.model.Movie
import com.fadybassem.domain.model.Movies
import com.fadybassem.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(sortBy: String): Flow<Resource<Movies>>
    fun get2024MoviesPagingSource(sortBy: String, year: Int, scope: CoroutineScope): Flow<Resource<PagingData<Movie>>>
    fun getMovieDetails(id: Int): Flow<Resource<Movie>>
    fun getMovieDetailsCredits(id: Int): Flow<Resource<Credits>>
    fun getMovieDetailsSimilar(id: Int): Flow<Resource<Movies>>
}
