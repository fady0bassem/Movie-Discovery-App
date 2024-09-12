package com.fadybassem.data.api

import com.fadybassem.data.remote.response.MoviesResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    suspend fun getPopularMovies(
        @Query("sort_by") sortBy: String,
    ): MoviesResponseModel

    @GET("movie/2024")
    suspend fun get2024Movies(
        @Query("page") page: Int,
    ): MoviesResponseModel
}
