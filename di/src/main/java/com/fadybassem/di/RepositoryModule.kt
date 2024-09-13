package com.fadybassem.di

import com.fadybassem.core.HandleApiError
import com.fadybassem.data.api.ApiService
import com.fadybassem.data.repository.MovieRepositoryImpl
import com.fadybassem.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        movieApi: ApiService,
        handleApiError: HandleApiError
    ): MovieRepository = MovieRepositoryImpl(
        movieApi = movieApi,
        handleApiError = handleApiError
    )
}