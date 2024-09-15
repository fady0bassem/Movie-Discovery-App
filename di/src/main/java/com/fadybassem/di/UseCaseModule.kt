package com.fadybassem.di

import com.fadybassem.domain.repository.MovieRepository
import com.fadybassem.domain.repository.WatchlistRepository
import com.fadybassem.domain.usecase.movie.Get2024MoviesPagingSourceUseCase
import com.fadybassem.domain.usecase.details.GetMovieDetailsCreditsUseCase
import com.fadybassem.domain.usecase.details.GetMovieDetailsSimilarUseCase
import com.fadybassem.domain.usecase.details.GetMovieDetailsUseCase
import com.fadybassem.domain.usecase.movie.GetPopularMoviesUseCase
import com.fadybassem.domain.usecase.watchlist.AddMovieToWatchlistUseCase
import com.fadybassem.domain.usecase.watchlist.GetWatchlistMoviesUseCase
import com.fadybassem.domain.usecase.watchlist.RemoveMovieFromWatchlistUseCase
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
    fun provideGetPopularMoviesUseCase(repository: MovieRepository): GetPopularMoviesUseCase =
        GetPopularMoviesUseCase(movieRepository = repository)

    @Singleton
    @Provides
    fun provideGet2024MoviesPagingSourceUseCase(repository: MovieRepository): Get2024MoviesPagingSourceUseCase =
        Get2024MoviesPagingSourceUseCase(repository = repository)

    @Singleton
    @Provides
    fun provideGetMovieDetailsUseCase(repository: MovieRepository): GetMovieDetailsUseCase =
        GetMovieDetailsUseCase(repository = repository)

    @Singleton
    @Provides
    fun provideGetMovieDetailsCreditsUseCase(repository: MovieRepository): GetMovieDetailsCreditsUseCase =
        GetMovieDetailsCreditsUseCase(repository = repository)

    @Singleton
    @Provides
    fun provideGetMovieDetailsSimilarUseCase(repository: MovieRepository): GetMovieDetailsSimilarUseCase =
        GetMovieDetailsSimilarUseCase(repository = repository)

    @Singleton
    @Provides
    fun provideAddMovieToWatchlistUseCase(repository: WatchlistRepository): AddMovieToWatchlistUseCase =
        AddMovieToWatchlistUseCase(repository = repository)

    @Singleton
    @Provides
    fun provideRemoveMovieFromWatchlistUseCase(repository: WatchlistRepository): RemoveMovieFromWatchlistUseCase =
        RemoveMovieFromWatchlistUseCase(repository = repository)

    @Singleton
    @Provides
    fun provideGetWatchlistMoviesUseCase(repository: WatchlistRepository): GetWatchlistMoviesUseCase =
        GetWatchlistMoviesUseCase(repository = repository)
}