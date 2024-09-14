package com.fadybassem.di

import com.fadybassem.core.ResourceProvider
import com.fadybassem.domain.repository.MovieRepository
import com.fadybassem.domain.usecase.GetPopularMoviesUseCase
import com.fadybassem.domain.usecase.Get2024MoviesPagingSourceUseCase
import com.fadybassem.domain.usecase.GetMovieDetailsCreditsUseCase
import com.fadybassem.domain.usecase.GetMovieDetailsSimilarUseCase
import com.fadybassem.domain.usecase.GetMovieDetailsUseCase
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

    @Singleton
    @Provides
    fun provideGet2024MoviesPagingSourceUseCase(
        repository: MovieRepository,
        networkManager: NetworkManager,
        resourceProvider: ResourceProvider
    ): Get2024MoviesPagingSourceUseCase = Get2024MoviesPagingSourceUseCase(
        repository = repository,
        networkManager = networkManager,
        resourceProvider = resourceProvider
    )

    @Singleton
    @Provides
    fun provideGetMovieDetailsUseCase(
        movieRepository: MovieRepository,
        networkManager: NetworkManager,
        resourceProvider: ResourceProvider
    ): GetMovieDetailsUseCase = GetMovieDetailsUseCase(
        repository = movieRepository,
        networkManager = networkManager,
        resourceProvider = resourceProvider
    )

    @Singleton
    @Provides
    fun provideGetMovieDetailsCreditsUseCase(
        movieRepository: MovieRepository,
        networkManager: NetworkManager,
        resourceProvider: ResourceProvider
    ): GetMovieDetailsCreditsUseCase = GetMovieDetailsCreditsUseCase(
        repository = movieRepository,
        networkManager = networkManager,
        resourceProvider = resourceProvider
    )

    @Singleton
    @Provides
    fun provideGetMovieDetailsSimilarUseCase(
        movieRepository: MovieRepository,
        networkManager: NetworkManager,
        resourceProvider: ResourceProvider
    ): GetMovieDetailsSimilarUseCase = GetMovieDetailsSimilarUseCase(
        repository = movieRepository,
        networkManager = networkManager,
        resourceProvider = resourceProvider
    )
}