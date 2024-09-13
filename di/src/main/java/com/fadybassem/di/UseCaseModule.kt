package com.fadybassem.di

import com.fadybassem.core.ResourceProvider
import com.fadybassem.domain.repository.MovieRepository
import com.fadybassem.domain.usecase.GetPopularMoviesUseCase
import com.fadybassem.util.NetworkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetPopularMoviesUseCase(
        movieRepository: MovieRepository,
        networkManager: NetworkManager,
        resourceProvider: ResourceProvider
    ): GetPopularMoviesUseCase = GetPopularMoviesUseCase(
        movieRepository = movieRepository,
        networkManager = networkManager,
        resourceProvider = resourceProvider
    )
}