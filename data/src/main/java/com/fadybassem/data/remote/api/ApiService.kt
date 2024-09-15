package com.fadybassem.data.remote.api

import com.fadybassem.data.remote.response.CreditsResponseModel
import com.fadybassem.data.remote.response.MovieResponseModel
import com.fadybassem.data.remote.response.MoviesResponseModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    suspend fun getPopularMovies(
        @Query("sort_by") sortBy: String,
    ): MoviesResponseModel

    @GET("discover/movie")
    suspend fun get2024Movies(
        @Query("sort_by") sortBy: String,
        @Query("primary_release_year") year: Int,
        @Query("page") page: Int,
    ): MoviesResponseModel

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Int,
    ): MovieResponseModel

    @GET("movie/{id}/credits")
    suspend fun getMovieDetailsCredits(
        @Path("id") id: Int,
    ): CreditsResponseModel

    @GET("movie/{id}/similar")
    suspend fun getMovieDetailsSimilar(
        @Path("id") id: Int,
    ): MoviesResponseModel
}
