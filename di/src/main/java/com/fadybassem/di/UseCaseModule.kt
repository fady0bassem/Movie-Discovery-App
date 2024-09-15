package com.fadybassem.di

import com.fadybassem.domain.repository.MovieRepository
import com.fadybassem.domain.usecase.Get2024MoviesPagingSourceUseCase
import com.fadybassem.domain.usecase.GetMovieDetailsCreditsUseCase
import com.fadybassem.domain.usecase.GetMovieDetailsSimilarUseCase
import com.fadybassem.domain.usecase.GetMovieDetailsUseCase
import com.fadybassem.domain.usecase.GetPopularMoviesUseCase
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
    fun provideGetPopularMoviesUseCase(movieRepository: MovieRepository): GetPopularMoviesUseCase =
        GetPopularMoviesUseCase(movieRepository = movieRepository)

    @Singleton
    @Provides
    fun provideGet2024MoviesPagingSourceUseCase(repository: MovieRepository): Get2024MoviesPagingSourceUseCase =
        Get2024MoviesPagingSourceUseCase(repository = repository)

    @Singleton
    @Provides
    fun provideGetMovieDetailsUseCase(movieRepository: MovieRepository): GetMovieDetailsUseCase =
        GetMovieDetailsUseCase(repository = movieRepository)

    @Singleton
    @Provides
    fun provideGetMovieDetailsCreditsUseCase(movieRepository: MovieRepository): GetMovieDetailsCreditsUseCase =
        GetMovieDetailsCreditsUseCase(repository = movieRepository)

    @Singleton
    @Provides
    fun provideGetMovieDetailsSimilarUseCase(movieRepository: MovieRepository): GetMovieDetailsSimilarUseCase =
        GetMovieDetailsSimilarUseCase(repository = movieRepository)
}