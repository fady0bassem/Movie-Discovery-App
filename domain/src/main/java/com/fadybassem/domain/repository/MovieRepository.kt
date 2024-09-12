package com.fadybassem.domain.repository

import com.fadybassem.domain.model.Movies
import com.fadybassem.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(sortBy: String): Flow<Resource<Movies>>
}
